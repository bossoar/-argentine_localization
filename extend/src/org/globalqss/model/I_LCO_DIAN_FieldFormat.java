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
package org.globalqss.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for LCO_DIAN_FieldFormat
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS
 */
public interface I_LCO_DIAN_FieldFormat 
{

    /** TableName=LCO_DIAN_FieldFormat */
    public static final String Table_Name = "LCO_DIAN_FieldFormat";

    /** AD_Table_ID=1000015 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name CalcColumnPosition */
    public static final String COLUMNNAME_CalcColumnPosition = "CalcColumnPosition";

	/** Set CalcColumnPosition	  */
	public void setCalcColumnPosition (int CalcColumnPosition);

	/** Get CalcColumnPosition	  */
	public int getCalcColumnPosition();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name FieldPrintFormat */
    public static final String COLUMNNAME_FieldPrintFormat = "FieldPrintFormat";

	/** Set Field Print Format	  */
	public void setFieldPrintFormat (String FieldPrintFormat);

	/** Get Field Print Format	  */
	public String getFieldPrintFormat();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name LCO_DIAN_FieldFormat_ID */
    public static final String COLUMNNAME_LCO_DIAN_FieldFormat_ID = "LCO_DIAN_FieldFormat_ID";

	/** Set DIAN Field Format	  */
	public void setLCO_DIAN_FieldFormat_ID (int LCO_DIAN_FieldFormat_ID);

	/** Get DIAN Field Format	  */
	public int getLCO_DIAN_FieldFormat_ID();

    /** Column name LCO_DIAN_Format_ID */
    public static final String COLUMNNAME_LCO_DIAN_Format_ID = "LCO_DIAN_Format_ID";

	/** Set DIAN Format	  */
	public void setLCO_DIAN_Format_ID (int LCO_DIAN_Format_ID);

	/** Get DIAN Format	  */
	public int getLCO_DIAN_Format_ID();

	public org.globalqss.model.I_LCO_DIAN_Format getLCO_DIAN_Format() throws RuntimeException;

    /** Column name LCO_DIAN_XMLPrintLabel_ID */
    public static final String COLUMNNAME_LCO_DIAN_XMLPrintLabel_ID = "LCO_DIAN_XMLPrintLabel_ID";

	/** Set DIAN XML Print Label	  */
	public void setLCO_DIAN_XMLPrintLabel_ID (int LCO_DIAN_XMLPrintLabel_ID);

	/** Get DIAN XML Print Label	  */
	public int getLCO_DIAN_XMLPrintLabel_ID();

	public org.globalqss.model.I_LCO_DIAN_XMLPrintLabel getLCO_DIAN_XMLPrintLabel() throws RuntimeException;

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
