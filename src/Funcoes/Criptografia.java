package Funcoes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
	public String criptografia (String string) throws NoSuchAlgorithmException {  
	    MessageDigest md = MessageDigest.getInstance("MD5");  
	    BigInteger hash = new BigInteger(1, md.digest(string.getBytes()));  
	    String s = hash.toString(16);  
	    if (s.length() %2 != 0)  
	        s = "0" + s;  
	    return s;  
	}
}
