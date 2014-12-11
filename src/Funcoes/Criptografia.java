package Funcoes;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	public String criptografia(String string) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, md.digest(string.getBytes()));
		String s = hash.toString(16);
		if (s.length() % 2 != 0)
			s = "0" + s;
		return s;
	}

	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
		}
		return sb.toString();
	}

	public static String md5Hex(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
}
