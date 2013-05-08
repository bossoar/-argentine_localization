package ar.edu.ifes.prin.fiscal.epson;

import java.io.IOException;
import java.util.Map;

import ar.com.ergio.print.fiscal.FiscalPacket;
import ar.com.ergio.print.fiscal.comm.FiscalComm;
import ar.com.ergio.print.fiscal.msg.FiscalMessage;

/**
 * Impresora Fiscal Epson. Funcionalidad comun a todos los modelos de Epson.
 * Implementa la interfaz <code>HasarCommands</code>. Cualquier modelo nuevo
 * de Epson a implementar debería ser una especialización de esta clase,
 * permitiendo sobreescribir algunos o todos los comandos implementados
 * por defecto en esta clase.
 * @author Bosso Armando
 * @date 04/05/2013
 */

public abstract class EpsonFiscalPrinter extends BasicFiscalPrinter implements EpsonCommands,EpsonConstants

{
	
	// Tipo de comprobante fiscal
	public static final int FACTURA = 1;
	public static final int RECIBO = 2;
	public static final int NOTA_DEBITO = 3;
	
	// Responsabilidad frente al IVA
	/** Responsabilidad frente al IVA: Responsable inscripto */
	protected static final String RESPONSABLE_INSCRIPTO = "I";
	/** Responsabilidad frente al IVA: Responsable no inscripto */
	protected static final String RESPONSABLE_NO_INSCRIPTO = "K";
	/** Responsabilidad frente al IVA: Exento */
	protected static final String EXENTO = "E";
	/** Responsabilidad frente al IVA: No responsable */
	protected static final String NO_RESPONSABLE = "N";
	/** Responsabilidad frente al IVA: Consumidor final */
	protected static final String CONSUMIDOR_FINAL = "F";
	/** Responsabilidad frente al IVA: Responsable no inscripto, venta de bienes de uso */
	protected static final String RESPONSABLE_NO_INSCRIPTO_BIENES_DE_USO = "B";
	/** Responsabilidad frente al IVA: Responsable monotributo */
	protected static final String RESPONSABLE_MONOTRIBUTO = "M";
	/** Responsabilidad frente al IVA: Monotributista social */
	protected static final String MONOTRIBUTISTA_SOCIAL = "T";
	/** Responsabilidad frente al IVA: PequeÃ±o contribuyente eventual */
	protected static final String PEQUENO_CONTRIBUYENTE_EVENTUAL = "C";
	/** Responsabilidad frente al IVA: PequeÃ±o contribuyente eventual social */
	protected static final String PEQUENO_CONTRIBUYENTE_EVENTUAL_SOCIAL = "V";
	/** Responsabilidad frente al IVA: No categorizado */
	protected static final String NO_CATEGORIZADO = "U";

	
	// Tipo de documento
	/** C.U.I.T. */
	protected static final String CUIT = "T";
	/** C.U.I.L. */
	protected static final String CUIL = "L";
	/** Libreta de enrolamiento */
	protected static final String LIBRETA_DE_ENROLAMIENTO = "E";
	/** Libreta cÃ­vica */
	protected static final String LIBRETA_CIVICA = "V";
	/** Documento nacional de identidad */
	protected static final String DNI = "D";
	/** Pasaporte */
	protected static final String PASAPORTE = "P";
	/** CÃ©dula de identidad */
	protected static final String CEDULA = "C";
	/** Sin calificador */
	protected static final String SIN_CALIFICADOR = " ";
	
	// Formato de código de barras
	/** Código de barras EAN 13 */
	protected static final Integer EAN_13 = 2;
	/** Código de barras EAN 8 */
	protected static final Integer EAN_8 = 3;
	/** Código de barras UPCA */
	protected static final Integer UPCA  = 0;
	/** Código de barras ITF 2 de 5 */
	protected static final Integer ITF = 5;
	
	
	// Opciones de operación del comando ReturnRecharge
	/** ReturnRecharge: Operación Devolución de Envases */
	protected static final String CONTAINER_RETURN = "e";
	/** ReturnRecharge: Operación Descuento / Recargo */
	protected static final String DISCOUNT_RECHARGE = "B";
	
	
	/** Conjunto de caracteres para realizar la conversión a string de los paquetes fiscales */
	private String encoding = "ISO8859_1";	// ISO 8859-1, Latin alphabet No. 1.
	/** Año base para validación de fechas */
	private int baseRolloverYear = 1997;
	/** Estado actual de la impresora */
	private int printerStatus;
	/** Estado actual del controlador fiscal */
	private int fiscalStatus;
	/** Posibles mensajes de estado de la impresora */
	private Map<Integer,FiscalMessage> printerStatusMsgs;
	/** Posibles mensajes de estado del controlador fiscal */
	private Map<Integer,FiscalMessage> fiscalStatusMsgs;
	/** Códigos de mensajes de estado de la impresora */
	private int[] printerStatusCodes = { PST_PRINTER_BUSY, PST_PRINTER_ERROR, PST_PRINTER_OFFLINE,
										 PST_JOURNAL_PAPER_OUT, PST_TICKET_PAPER_OUT, PST_PRINT_BUFFER_FULL,
										 PST_PRINT_BUFFER_EMPTY, PST_PRINTER_COVER_OPEN, PST_MONEY_DRAWER_CLOSED
										};
	
	
	/** Mapeo entre categorias de IVA de las clases de documentos y los valores
	 * esperados por las impresoras fiscales de esta marca. */
	private Map<Integer, String> ivaResponsabilities;
	/** Mapeo entre los tipos de identificación de clientes de las clases
	 * de documentos y los valores esperados por las impresoras de esta marca. */
	private Map<Integer, String> identificationTypes;
	/** Mapeo entre los tipos de documentos de las clases de documentos y
	 * los valores esperados por las impresoras de esta marca. */
	private Map<String, String> documentTypes;
	
	public EpsonFiscalPrinter() {
		super();
	}
	
	/**
	 * @param fiscalComm
	 */
	
	public EpsonFiscalPrinter(FiscalComm fiscalComm) {
		super(fiscalComm);
	}
	
	public FiscalPacket cmdBarCode(Integer codeType, String data, boolean printNumbers) {
		FiscalPacket cmd = createFiscalPacket(CMD_SET_BAR_CODE);
		int i = 1;
		cmd.setNumber(i++, codeType, false);
		cmd.setText(i++, data, false);
		cmd.setBoolean(i++, printNumbers, "N", "x", false);
		cmd.setText(i++, "x", false);
		return cmd;
	}
	
	public FiscalPacket cmdCancelDocument() {
		FiscalPacket cmd = createFiscalPacket(CMD_CANCEL_DOCUMENT);
		return cmd;
	}

	public FiscalPacket cmdChangeIVAResponsibility(String ivaResponsability) {
		FiscalPacket cmd = createFiscalPacket(CMD_CHANGE_IVA_RESPONSIBILITY);
		int i = 1;
		cmd.setText(i++, ivaResponsability, false);
		return cmd;
	}

	public FiscalPacket cmdCloseDNFH(Integer copies) {
		FiscalPacket cmd = createFiscalPacket(CMD_CLOSE_DNFH);
		int i = 1;
		cmd.setNumber(i++, copies, true);
		return cmd;
	}

	public FiscalPacket cmdCloseFiscalReceipt(Integer copies) {
		FiscalPacket cmd = createFiscalPacket(CMD_CLOSE_FISCAL_RECEIPT);
		int i = 1;
		cmd.setNumber(i++, copies, true);
		return cmd;
	}

	public FiscalPacket cmdCloseNonFiscalReceipt(Integer copies) {
		FiscalPacket cmd = createFiscalPacket(CMD_CLOSE_NON_FISCAL_RECEIPT);
		int i = 1;
		cmd.setNumber(i++, copies, true);
		return cmd;
	}

	public FiscalPacket cmdDailyClose(String docType) {
		FiscalPacket cmd = createFiscalPacket(CMD_DAILY_CLOSE);
		int i = 1;
		cmd.setText(i++, docType, false);
		return cmd;
	}

	public FiscalPacket cmdDoubleWidth() {
		FiscalPacket cmd = createFiscalPacket(CMD_DOUBLE_WIDTH);
		return cmd;
		
		
	
	
	
}
