package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UsuarioJPA;

public class UsuarioService {

	public Object addUsuario(Usuario usu) {
		UsuarioJPA usuario = new UsuarioJPA();
		usuario.addUsuario(usu);
		
		List<Usuario> teste = usuario.list();
		
		for (Usuario Usuario : teste) {
			System.out.println(Usuario.getNome());
		}
		return teste;
	}

	public List<Usuario> listUsuario() throws Exception {
		UsuarioJPA listUsuario = new UsuarioJPA();
		return listUsuario.list();
	}
	public Object deleteUsuario(JSONArray usu) throws Exception{
		UsuarioJPA uni = new UsuarioJPA();
			
		if(usu != null && usu.length() > 0){
			for (int i = 0; i < usu.length(); i++) {
				uni.deleteUsuario(usu.getLong(i));
				System.out.println(usu.getLong(i));
			}
		}
		return uni.list();
	}

	public Object editarUsuario(Usuario usu) {
		UsuarioJPA user = new UsuarioJPA();
		user.editarUsuario(usu);
		return user.list();
	}
	
}
