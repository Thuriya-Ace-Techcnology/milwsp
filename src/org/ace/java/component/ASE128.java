package org.ace.java.component;

//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ASE128 {

	public static String encrypt(String key, String initVector, String value) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			// System.out.println("encrypted string: "
			// + Base64.encodeBase64String(encrypted));
			// return Base64.encodeBase64String(encrypted);
			String s = new String(Base64.getEncoder().encode(encrypted));
			return s;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// public static String encrypt(String key, String initVector, String value)
	// {
	// try {
	// IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	// SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	// cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	// byte[] encrypted = cipher.doFinal(value.getBytes());
	// return Base64.encodeBase64String(encrypted);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// return null;
	// }

	// public static String decrypt(String key, String initVector, String
	// encrypted) {
	// try {
	// IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	// SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	// Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	// cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	// byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
	// return new String(original);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// return null;
	// }

	public static void main(String[] args) {
		// try {
		String key = "A791B7F6229B4F3A"; // 128 bit key
		String intVector = "GGIP000000000123";// 16 bytes IV (16char)

		// String key = "A791B7F6229B4F3A"; // 128 bit key
		// String intVector = "GGIP000000000012";// 16 bytes IV (16char)

		// ok$ data
		// String response =
		// "zSYJOuSw%2FxSDMAi48BGmYu0GkmDn%2F5j1OzZ806XcLGxwCfn7v4Z5L8hHZjiyVk8pjlHsytI0MJIAk5oSGSRgVKTC6SxBMtDCkKRIuoXAZtb%2F4kCe72ZsTniPpRl9%2FqYiI64crjpBAXTJ5601offfPAmgb9aT6ygpxjbwldl2UL%2BZABCtpqFYO%2B3PZsb42HhHYq9UzStKo5FS0CYPm%2BGK9Q%2Bmtl%2B89OPU0FlYc9ADLjVieDidltNPoH4h%2BaPzGwHDBcih25cR7WQ%2Fn4LELsBxfJK8dK1iqJe%2FWVCkclh69E8Rqwm2dibvUJUb9qGlIh0vgRKp29d8FMuC6XfKOONu%2BGNQAWnPbpxoEe3keY4u4ssdQgz3OnE77FVFMGr3ygHS0zPyYgXCIJLhwZzRZZjxaOoEz14Mo69i38CpJOibh62%2FaJvzGKHI4WW9x8IKF5jauJvvG1vpJ0HEjA1nqaZYEn0%2BDxE6hvjBi1ecwMpe2vmGzZZf%2BJ6d4v4KMhAX4EU3IZsCjHfqM1vv%2FgKhDXKUe9gtqicdz41XoHiRreN6CRZHywv57i6uLThe0G2VCR2E5J4If7WljQMD7VfFe7PSWutfep%2F6WUHnIGVWFNnM7Us%3D%2CGGIP000000000002%2C00959768187249";
		// String encrypted = java.net.URLDecoder.decode(response, "UTF-8");

		// String[] test = encrypted.split(",");
		// encrypted = test[0];
		// initVector = test[1];
		// System.out.println("encrypted - " + encrypted);
		// System.out.println("initVector - " + initVector);

		// test data
		String encrypted = "vhL1jJHDxAEY/swDjwv211VgFZq8ng7wePJO69eOYOlxXdjATHAO9hBOYr4B3Ak7VzNioDvmLLVZOGQ5cskbUZxdUB5E8Vb/zOJmnMTkwE+xxAXX3v0Z4VJkX1LlxnVNotE0ZB2wg1qNv74iptHAEbxKhz7aMv8x/epZGm1i/a/0Vgwl2Lab+IRDD7V8kjzGfe3Fvb9bBK1kp66o/kL2nc9wNGfUoVIJMN+I2xMn4I+tMeKY6GmcdXQD0o/kss8yhXJZ9ZXvVYFIngw3QG7JXOv39RJompSFDAGZL/4h0kGoe7UT3g9ypbEB/Xj4qRlAMdL4iTEXfaBTQn4x5JNBhoFtc7cJeOn/4mxtVcXLRMk02W8yQEsgeP/0d60UXwzb8rt5w5pofl1mdFUvZj+J8pYfqI+Ju6Bqjq2cvbP5TC0=";

		String decrypted = ASE128.decrypt(key, intVector, encrypted);
		System.out.println("Decrypted - " + decrypted);

		// String key = "A791B7F6229B4F3A";// 128 bit key (16char)
		// String intVector = "GGIP000000000002";// 16 bytes IV (16char)
		//
		// // String key = "AAAAAAAAAAAAAAAA";// 128 bit key (16char)
		// // String intVector = "1234567891234567";// 16 bytes IV (16char)
		//
		// String text =
		// "{\"ResponseCode\":\"0\",\"Destination\":\"00959971813997\",\"Source\":\"00959787190850\",\"Amount\":\"4.06\",\"TransactionId\":\"62795067\",\"TransactionTime\":\"13-Jul-2016
		// 19:33:59\",\"AgentName\":\"MR,PERSONAL\",\"Kickvalue\":\"\",\"Loyaltypoints\":\"0\",\"Description\":\"Transaction
		// Successful\",\"MerRefNo\":\"CGMEComAx20161331931653\",”CustomerNumber”:””}";
		//
		// String encrypted = ASE128.encrypt(key, intVector, text);
		// System.out.println("Encrypted - " + encrypted);
		//
		// String decrypted = ASE128.decrypt(key, intVector, encrypted);
		// System.out.println("Decrypted - " + decrypted);

		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
	}
}
