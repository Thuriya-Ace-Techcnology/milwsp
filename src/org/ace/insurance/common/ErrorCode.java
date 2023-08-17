/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.common;

public class ErrorCode {
	// TODO DELETE
	/* Common DB */
	public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
	public static final String DAO_RUNTIME_ERROR = "DAO_RUNTIME_ERROR";
	public static final String NO_SQL_ERROR_CODE_CONFIG = "NO_SQL_ERROR_CODE_CONFIG";

	/* Email Error Code */
	public static final String EMAIL_AUTHENTICATION_FAILED = "EMAIL_AUTHENTICATION_FAILED";
	public static final String MAIL_SERVER_CONNECTION_FAILED = "MAIL_SERVER_CONNECTION_FAILED";
	public static final String MAIL_SEND_SUCCESS = "MAIL_SEND_SUCCESS";

	/* Business Logic Error Code */
	public static final String NO_PREMIUM_RATE = "NO_PREMIUM_RATE";
	// public static final String NO_RISK_RATE = "NO_RISK_RATE";
	public static final String MEDICAL_AGE_KEYFACTOR_NOTMATCH = "MEDICAL_AGE_KEYFACTOR_NOTMATCH";
	public static final String AUTHENGICATION_FAILED = "AUTHENGICATION_FAILED";
	public static final String OLD_PASSWORD_DOES_NOT_MATCH = "OLD_PASSWORD_DOES_NOT_MATCH";
	public static final String NO_BUILDING_CLASS = "NO_BUILDING_CLASS";
	public static final String NO_SUM_INSURED_KEYFACTOR = "NO_SUM_INSURED_KEYFACTOR";
	// public static final String NO_ENDORSECHANGE_SETTING =
	// "NO_ENDORSECHANGE_SETTING";
	// public static final String NO_APPLIED_ENDORSECHANGE_SETTING =
	// "NO_APPLIED_ENDORSECHANGE_SETTING";
	// public static final String NO_SHORTPERIODRATE_SETTING =
	// "NO_SHORTPERIODRATE_SETTING";
	//
	// public static final String NO_SHORT_PERIOD_RATE = "NO_SHORT_PERIOD_RATE";
	//
	// public static final String VIOLATION_CONSTRAINT = "VIOLATION_CONSTRAINT";
	//
	public static final String FCM_CLIENT_SIDE_ERROR = "FCM_CLIENT_SIDE_ERROR";
	public static final String FCM_SERVER_SIDE_ERROR = "FCM_SERVER_SIDE_ERROR";
	public static final String FCM_SERVER_UNAVAILABLE = "FCM_SERVER_UNAVAILABLE";
	public static final String FCM_PUSH_NOTIFICATION_ERROR = "FCM_PUSH_NOTIFICATION_ERROR";
	public static final String FCM_URL_PATTERN_ERROR = "FCM_URL_PATTERN_ERROR";
	public static final String FCM_MESSAGE_FORMAT_ERROR = "FCM_MESSAGE_FORMAT_ERROR";
}