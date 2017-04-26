package br.com.projetoEstagio.service;

import java.util.List;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UsuarioJPA;

public class UsuarioService {

	public Object addUsuario(Usuario usu) {
		UsuarioJPA user = new UsuarioJPA();
		user.addUsuario(usu);
		return user;
	}

	public List<Usuario> listUsuario() throws Exception {
		UsuarioJPA listUsuario = new UsuarioJPA();
		return listUsuario.list();
	}

	
}
