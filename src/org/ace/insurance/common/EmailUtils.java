package org.ace.insurance.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.ace.java.component.SystemException;
import org.apache.commons.validator.routines.EmailValidator;

import com.sun.mail.smtp.SMTPAddressSucceededException;
import com.sun.mail.smtp.SMTPSendFailedException;

public class EmailUtils {
	public static final String MAIL_SERVER = "MAIL_SERVER";
	public static final String MAIL_PORT = "MAIL_PORT";

	public static final String SYSTEM_EMAIL_ADDRESS = "SYSTEM_EMAIL_ADDRESS";
	public static final String SYSTEM_EMAIL_PASSWORD = "SYSTEM_EMAIL_PASSWORD";
	public static final String COMPANY_EMAIL_ADDRESS = "COMPANY_EMAIL_ADDRESS";
	public static final String EMAIL_SIGNATURE = "EMAIL_SIGNATURE";
	public static final String EMAIL_BODY = "EMAIL_BODY";
	private static Properties mailConfig;

	static {
		try {
			mailConfig = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream in = classLoader.getResourceAsStream("mail-config.properties");
			mailConfig.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(ErrorCode.SYSTEM_ERROR, "Failed to load mail-config.properties");
		}
	}

	public static String getProperty(String s) {
		return mailConfig.getProperty(s);
	}

	public static boolean sendEmail(EmailInfo emailInfo) throws Exception {
		boolean success = false;
		try {
			String toEmail = null;
			EmailValidator validator = EmailValidator.getInstance();
			if (validator.isValid(getProperty(SYSTEM_EMAIL_ADDRESS))) {
				Properties props = System.getProperties();
				toEmail = emailInfo.getToEmail() == null ? getProperty(COMPANY_EMAIL_ADDRESS) : emailInfo.getToEmail();
				props.setProperty("mail.smtp.host", getProperty(MAIL_SERVER));
				props.setProperty("mail.smtp.port", String.valueOf(getProperty(MAIL_PORT)));
				props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.smtp.starttls.enable", "true");
				// props.setProperty("mail.smtp.reportsuccess", "true");
				props.setProperty("mail.smtp.dsn.notify", "SUCCESS,FAILURE,DELAY ORCPT=rfc1891;" + getProperty(SYSTEM_EMAIL_ADDRESS));
				props.setProperty("mail.smtp.dsn.ret", "FULL");

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getProperty(SYSTEM_EMAIL_ADDRESS), getProperty(SYSTEM_EMAIL_PASSWORD));
					}
				});
				session.setDebug(true);
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(getProperty(SYSTEM_EMAIL_ADDRESS), "MI System Admin"));
				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
				// Set Subject: header field
				message.setSubject(emailInfo.getSubject());
				// Now set the actual message
				String text = "Dear " + emailInfo.getUserName() + ", <br><br>" + emailInfo.getTextBody() + "<br><br> " + getProperty(EMAIL_SIGNATURE);
				InternetHeaders headers = new InternetHeaders();
				headers.addHeader("Content-type", "text/html; charset=UTF-8");
				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart(headers, text.getBytes("UTF-8"));
				// Create a multipar message
				Multipart multipart = new MimeMultipart();
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				// Part two is attachment
				if (emailInfo.getFilePath() != null) {
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(emailInfo.getFilePath());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(emailInfo.getFilePath());
					multipart.addBodyPart(messageBodyPart);
				}
				// Send the complete message parts
				message.setContent(multipart);
				// Send message
				Transport.send(message);
				success = true;
			} else {
				throw new SystemException("Format of sender email : " + toEmail + " is not valid.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof AuthenticationFailedException) {
				throw new SystemException(ErrorCode.EMAIL_AUTHENTICATION_FAILED, "");
			} else if (e instanceof MessagingException) {
				if (((MessagingException) e).getNextException() instanceof NoRouteToHostException) {
					throw new SystemException(ErrorCode.MAIL_SERVER_CONNECTION_FAILED, "");
				} else if (((MessagingException) e).getNextException() instanceof UnknownHostException) {
					throw new SystemException(ErrorCode.MAIL_SERVER_CONNECTION_FAILED, "");
				} else if (((MessagingException) e).getNextException() instanceof ConnectException) {
					throw new SystemException(ErrorCode.MAIL_SERVER_CONNECTION_FAILED, "");
				} else if (e instanceof SMTPSendFailedException) {
					if (((MessagingException) e).getNextException() instanceof SMTPAddressSucceededException) {
						String succeededMails = null;
						while (((MessagingException) e).getNextException() != null) {
							e = ((MessagingException) e).getNextException();
							String message = ((SMTPAddressSucceededException) e).getCommand().split("RCPT TO:<")[1].split(">")[0];
							if (succeededMails == null) {
								succeededMails = message;
							} else {
								succeededMails += " , " + message;
							}
						}
						throw new SystemException(ErrorCode.MAIL_SEND_SUCCESS, succeededMails);
					}
				}
			}
			throw e;
		}
		return success;
	}

	public static boolean sendEmailToRTA(EmailInfo emailInfo) throws Exception {
		boolean success = false;
		try {
			String toEmail = null;
			EmailValidator validator = EmailValidator.getInstance();
			if (validator.isValid(getProperty(SYSTEM_EMAIL_ADDRESS))) {
				Properties props = System.getProperties();
				toEmail = emailInfo.getToEmail() == null ? getProperty(COMPANY_EMAIL_ADDRESS) : emailInfo.getToEmail();
				props.setProperty("mail.smtp.host", getProperty(MAIL_SERVER));
				props.setProperty("mail.smtp.port", String.valueOf(getProperty(MAIL_PORT)));
				props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.smtp.starttls.enable", "true");
				// props.setProperty("mail.smtp.reportsuccess", "true");
				props.setProperty("mail.smtp.dsn.notify", "SUCCESS,FAILURE,DELAY ORCPT=rfc1891;" + getProperty(SYSTEM_EMAIL_ADDRESS));
				props.setProperty("mail.smtp.dsn.ret", "FULL");

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getProperty(SYSTEM_EMAIL_ADDRESS), getProperty(SYSTEM_EMAIL_PASSWORD));
					}
				});
				session.setDebug(true);
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(getProperty(SYSTEM_EMAIL_ADDRESS), "MI System Admin"));
				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailInfo.getCcEmail()));
				// Set Subject: header field
				message.setSubject(emailInfo.getSubject());
				// Now set the actual message
				String text = "Dear " + emailInfo.getUserName() + ", <br><br>" + String.valueOf(getProperty(EMAIL_BODY)) + "<br><br> " + getProperty(EMAIL_SIGNATURE);
				InternetHeaders headers = new InternetHeaders();
				headers.addHeader("Content-type", "text/html; charset=UTF-8");
				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart(headers, text.getBytes("UTF-8"));
				// Create a multipar message
				Multipart multipart = new MimeMultipart();
				// Set text message part
				multipart.addBodyPart(messageBodyPart);
				// Part two is attachment
				if (emailInfo.getFilePath() != null) {
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(emailInfo.getFilePath());
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(emailInfo.getFilePath());
					multipart.addBodyPart(messageBodyPart);
				}
				// Send the complete message parts
				message.setContent(multipart);
				// Send message
				Transport.send(message);
				success = true;
			} else {
				throw new SystemException("Format of sender email : " + toEmail + " is not valid.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof AuthenticationFailedException) {
				throw new SystemException(ErrorCode.EMAIL_AUTHENTICATION_FAILED, "");
			} else if (e instanceof MessagingException) {
				if (((MessagingException) e).getNextException() instanceof NoRouteToHostException) {
					throw new SystemException(ErrorCode.MAIL_SERVER_CONNECTION_FAILED, "");
				} else if (((MessagingException) e).getNextException() instanceof UnknownHostException) {
					throw new SystemException(ErrorCode.MAIL_SERVER_CONNECTION_FAILED, "");
				} else if (((MessagingException) e).getNextException() instanceof ConnectException) {
					throw new SystemException(ErrorCode.MAIL_SERVER_CONNECTION_FAILED, "");
				} else if (e instanceof SMTPSendFailedException) {
					if (((MessagingException) e).getNextException() instanceof SMTPAddressSucceededException) {
						String succeededMails = null;
						while (((MessagingException) e).getNextException() != null) {
							e = ((MessagingException) e).getNextException();
							String message = ((SMTPAddressSucceededException) e).getCommand().split("RCPT TO:<")[1].split(">")[0];
							if (succeededMails == null) {
								succeededMails = message;
							} else {
								succeededMails += " , " + message;
							}
						}
						throw new SystemException(ErrorCode.MAIL_SEND_SUCCESS, succeededMails);
					}
				}
			}
			throw e;
		}
		return success;
	}
	
	
	// Activation Mail to Customer.
	public static boolean sentSignupMail(String email, String UserName, String token) {
		boolean result = false;
		String text = "Thanks for signing up! Your account has been created, your activate code is " + token;
		EmailInfo emailInfo = new EmailInfo(email, "Activate Your Account", UserName, text, null,null);
		try {
			result = sendEmail(emailInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		sentSignupMail("yinyinkhine1993@gmail.com", "YIN YIN KHINE", "ActivatedCode");
	}
}
