package br.com.projeto.help;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;


public class Crypt {
	
	public String md5(String pass) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		
		String salt="";
	       
	       BigInteger hash = new BigInteger(1, md.digest((salt+pass).getBytes()));
	       String senhaMd5 = String.format("%32x", hash);
	       return senhaMd5;
	}
	
	public String bs64d(String pass) throws NoSuchAlgorithmException{
		byte[] valueDecoded= Base64.decodeBase64(pass);
		return new String(valueDecoded);
	}

}
