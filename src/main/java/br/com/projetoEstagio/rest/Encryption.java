package br.com.projetoEstagio.rest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;

public class Encryption {

	public static String md5(String text) throws NoSuchAlgorithmException{
		
		String encry = "";
		
		MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(text.getBytes(),0,text.length());
	    
	    encry = new BigInteger(1,m.digest()).toString(16);
		
	    return encry;
	}
	
	public String bs64d(String pass) throws NoSuchAlgorithmException{
		byte[] valueDecoded= Base64.decodeBase64(pass);
		return new String(valueDecoded);
	}
}
