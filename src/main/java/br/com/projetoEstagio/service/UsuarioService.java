package br.com.projetoEstagio.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.JSONArray;

import br.com.projeto.help.Crypt;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UsuarioJPA;

public class UsuarioService {

	public Object addUsuario(Usuario usu) throws Exception, UnsupportedEncodingException, NoSuchAlgorithmException {
		
		UsuarioJPA usuario = new UsuarioJPA();
		List<Usuario> var = usuario.validate(usu);
		if(var.isEmpty()){
			Crypt crypt = new Crypt();
			usu.setSenha(crypt.decode(usu.getSenha()));
			usuario.addUsuario(usu);
		}else{
			throw new Exception();
		}
		return  usuario.list();

	}

	public List<Usuario> listUsuario() throws Exception {
		UsuarioJPA listUsuario = new UsuarioJPA();
		return listUsuario.list();
	}
	
	public List<Usuario> listCoordenadores() throws Exception {
		UsuarioJPA listUsuario = new UsuarioJPA();
		return listUsuario.listCoordenadores();
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
