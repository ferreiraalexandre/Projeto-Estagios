package br.com.projeto.help;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Crypt {
	
	public String decode(String pass) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		
		String salt="$r$%¨&YHGTY()2#";
	       
	       BigInteger hash = new BigInteger(1, md.digest((salt+pass).getBytes()));
	       String senhaMd5 = String.format("%32x", hash);
	       return senhaMd5;
	}


}
