package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UnidadeEnsinoJPA;
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
	public void deleteUsuario(JSONArray usu) throws Exception{
		UsuarioJPA uni = new UsuarioJPA();
			for (int i = 0; i < usu.length(); i++) {
				uni.deleteUsuario(usu.getLong(i));
				System.out.println(usu.getLong(i));
			}
		
	}

	public Object editarUsuario(Usuario usu) {
		UsuarioJPA user = new UsuarioJPA();
		user.editarUsuario(usu);
		return user;
	}
	
}
