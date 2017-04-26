package br.com.projetoEstagio.service;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UsuarioJPA;


public class UsuarioService {

	public Object addUsuario(Usuario usu) {
		UsuarioJPA user = new UsuarioJPA();
		user.addUsuario(usu);
		return user;
	}

}
