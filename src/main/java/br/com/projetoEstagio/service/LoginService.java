package br.com.projetoEstagio.service;

import java.security.NoSuchAlgorithmException;

import javax.persistence.NoResultException;

import br.com.projeto.help.Crypt;
import br.com.projetoEstagio.auth.Auth;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.LoginJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;


public class LoginService{
	
	public Usuario buscarUsuario(String email, String password){
		Crypt crypt = new Crypt();
		UsuarioJPA jpa = new UsuarioJPA();
		Usuario user = new Usuario();
		try {
			user.setSenha(crypt.md5(crypt.bs64d(password)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		user = jpa.getUserAuth(email, user.getSenha());
		if(user != null){
			Auth hashAuth = new Auth();
			
			
			user.setToken(hashAuth.generate(user.getEmail()));
		}
		return user;
		
	}
	
	/*public Usuario auth(Usuario e){
		try{
			Encryption descrpt = new Encryption();
			Auth hashAuth = new Auth();
			e.setPassword(descrpt.md5(descrpt.bs64d(e.getPassword())));
			Usuario user = this.dao.getObject(e.getEmail(), e.getPassword());
			if(user != null){
				user.setToken(hashAuth.generate(user.getEmail(), user.getPermission()));
				if(user.getAdm()){
					user.setAdm(true);
				}
				return user;
			}
			throw new Exception();
		}catch(NoResultException AE){
			throw new Exception();
		}
	}*/
}
