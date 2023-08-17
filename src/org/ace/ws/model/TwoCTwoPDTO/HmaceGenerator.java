package org.ace.ws.model.TwoCTwoPDTO;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmaceGenerator {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA256";
	private static Formatter formatter;

	private static String toHexString(byte[] bytes) {
		formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}

		return formatter.toString();
	}

	public static String calculateRFC2104HMAC(String data, String key)
		throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
		Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
		mac.init(signingKey);
		return toHexString(mac.doFinal(data.getBytes()));
	}

	/*public static void main(String[] args) throws Exception {
		String hmac = calculateRFC2104HMAC("104104000000441testing case 116012806695104000000002500http://localhost/devPortal/V3_UI_PHP_JT01_devPortal/result.php", "88BC0428D4CFB6A8823B334C19D052F79B0989D8AF72DEF765695682A356F910");

		System.out.println(hmac);
	}*/
}