package br.com.projetoEstagio.auth;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

public class Auth {
	
	public String generate(String email, int permission){
		final String issuer = "https://mydomain.com/";
		final String secret = "123spArta#@!@";
 
		final long iat = System.currentTimeMillis() / 1000L; // issued at claim 
		final long exp = iat + 6000L; // expires claim. In this case the token expires in 60 seconds

		final JWTSigner signer = new JWTSigner(secret);
		final HashMap<String, Object> claims = new HashMap<String, Object>();
		//claims.put("iss", issuer);
		claims.put("exp", exp);
		claims.put("iat", iat);
		claims.put("email", email);
		claims.put("permission", permission);

		final String jwt = signer.sign(claims);
		return jwt;
	}
	
	public String valid(String hash) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException{
		final String secret = "123spArta#@!@";
		System.out.println("Recebeu isso - "+hash);
		try {
		    final JWTVerifier verifier = new JWTVerifier(secret);
		    final Map<String, Object> claims= verifier.verify(hash);
		    System.out.println("Transformou nisso - "+claims);
		} catch (JWTVerifyException e) {
		    // Invalid Token
		}
		return hash;
	}
	
	public String validate(String hash) throws Exception{
		final String secret = "123spArta#@!@";
		try {
		    final JWTVerifier verifier = new JWTVerifier(secret);
		    final Map<String, Object> claims= verifier.verify(hash);
		} catch (JWTVerifyException e) {
			throw new Exception("Token expirado.");
		}
		return hash;
	}
	
	public String emailLogged(String hash) throws Exception{
		final String secret = "123spArta#@!@";
		try{  
			final JWTVerifier verifier = new JWTVerifier(secret);
			final Map<String, Object> claims= verifier.verify(hash.replace("Bearer ", ""));
			return claims.get("email").toString();
		}catch(JWTVerifyException e){
			throw new Exception("Erro ao recuperar email.");
		}
	}
	
	public String permission(String hash) throws Exception{
		final String secret = "123spArta#@!@";
		try{  
			final JWTVerifier verifier = new JWTVerifier(secret);
			final Map<String, Object> claims= verifier.verify(hash.replace("Bearer ", ""));
			return claims.get("permission").toString();
		}catch(JWTVerifyException e){
			throw new Exception("Erro ao recuperar permissao.");
		}
	}
}
