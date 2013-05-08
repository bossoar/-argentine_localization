/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package ar.com.ergio.print.fiscal.exception;

import java.io.IOException;

import ar.com.ergio.print.fiscal.FiscalPacket;
import ar.com.ergio.print.fiscal.msg.MsgRepository;

/** Clase base de todas las excepciones que pueden
 * generar las impresoras fiscales */
public class FiscalPrinterIOException extends IOException
{
    private static final long serialVersionUID = 4370079950592531083L;
    private static String STR_REQUEST = MsgRepository.get("FiscalRequest");
	private static String STR_RESPONSE = MsgRepository.get("FiscalResponse");
	
	private FiscalPacket request;
	private FiscalPacket response;
	private String fullMessage = null;

	public FiscalPrinterIOException() {}
	public FiscalPrinterIOException(String s) { super(s); }

	public FiscalPrinterIOException(FiscalPacket request, FiscalPacket response) { this(null, request, response); }
	public FiscalPrinterIOException(String s, FiscalPacket request, FiscalPacket response)
	{
		super(s);
		this.request = request;
		this.response = response;
	}

	private static String buildMessage(String s, FiscalPacket request, FiscalPacket response)
	{
		String d;
		if (request != null)
		{
			if (response != null) d = STR_REQUEST + ": " + request + ", "+ STR_RESPONSE + ": " + response;
			else d = STR_REQUEST + ": " + request;
		}
		else
		{
			if (response != null) d = STR_RESPONSE + ": " + response;
			else d = null;
		}
		if (d != null)
		{
			if (s != null) s += "\n(" + d + ")";
			else s = d;
		}
		return s;
	}
	
	public String getFullMessage() {
		if(fullMessage == null)
			fullMessage = buildMessage(getMessage(), getRequestPacket(), getResponsePacket());
		return fullMessage;
	}

	/** Get the request associated with this exception, if any. */
	public FiscalPacket getRequestPacket() { return request; }
	/** Get the response associated with this exception, if any. */
	public FiscalPacket getResponsePacket() { return response; }
}
