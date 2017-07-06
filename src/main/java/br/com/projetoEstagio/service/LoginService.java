package br.com.projetoEstagio.service;

import javax.persistence.NoResultException;

import br.com.projetoEstagio.auth.Auth;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.LoginJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;
import br.com.projetoEstagio.rest.Encryption;

public class LoginService{
	
	public Usuario buscarUsuario(String email, String password){
		
		UsuarioJPA user = new UsuarioJPA();
		
		Usuario usuario = user.buscar(email);
		
		if(usuario.getSenha().compareTo(password) == 0){
			System.out.println("sucesso");
		}
				
		return usuario;
		
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
