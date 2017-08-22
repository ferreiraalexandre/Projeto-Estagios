package br.com.projetoEstagio.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projeto.help.Crypt;
import br.com.projetoEstagio.connection.EntityManagerUtil;
import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;
import br.com.projetoEstagio.restUtil.RestResponse;

public class UsuarioService {

	public Object addUsuario(Usuario usu) throws Exception, UnsupportedEncodingException, NoSuchAlgorithmException {
		
		UsuarioJPA usuario = new UsuarioJPA();
		Object obj = null;
		
		List<Usuario> retorno = usuario.buscarPorEmail(usu);
		if(retorno.size() > 0){
			return obj;
		}else{
			Crypt crypt = new Crypt();
			usu.setSenha(crypt.md5(crypt.bs64d(usu.getSenha())));
			usuario.addUsuario(usu);	
			
			List<Usuario> usuarios = usuario.list();
			for (Usuario u : usuarios) {
				EntityManagerUtil.getEMIntance().detach(u);
				u.setSenha(null);
			}
			return  usuarios;
		}
	}
	
	public List<Usuario> listUsuario() throws Exception {
		UsuarioJPA listUsuario = new UsuarioJPA();
		List<Usuario> usuarios = listUsuario.list();
		
		for (Usuario u : usuarios) {
			EntityManagerUtil.getEMIntance().detach(u);
			u.setSenha(null);
		}
		return usuarios;
	}
	
	public List<Usuario> listCoordenadores() throws Exception {
		UsuarioJPA listUsuario = new UsuarioJPA();
		List<Usuario> coordenadores = listUsuario.listCoordenadores();
	
		for (Usuario u : coordenadores) {
			EntityManagerUtil.getEMIntance().detach(u);
			u.setSenha(null);
		}

		return coordenadores;
	}

	public Object deleteUsuario(JSONArray users, RestResponse response) throws Exception{
		UsuarioJPA use = new UsuarioJPA();
		EmpresaJPA emp = new EmpresaJPA();
		TurmaJPA tur = new TurmaJPA();
		
		List<Usuario> usuarioEmUso = new ArrayList<Usuario>();
			
			if(users != null && users.length() > 0){
				for (int i = 0; i < users.length(); i++) {
					List<Empresa> empresa = emp.buscarPorUsuario(users.getLong(i));
					List<Turma> turma = tur.buscarPorUsuario(users.getLong(i));
					
					if(empresa.size() > 0 || turma.size() > 0){
						if(empresa.size() > 0){
							usuarioEmUso.add(empresa.get(0).getUsuario());
						}
						if(turma.size() > 0){
							usuarioEmUso.add(turma.get(0).getUsuario());
						}
					}else{
						use.deleteUsuario(users.getLong(i));												
					}
				}
			}
			
			if(usuarioEmUso.size() > 0){
				String nomeUsuario = "";
				for (Usuario usuario : usuarioEmUso) {
					if(!nomeUsuario.contains(usuario.getNome())){		
						nomeUsuario += usuario.getNome() + " - ";						
					}
				}
				response.setDescription(nomeUsuario.replace(" * ", ", "));
			}
			
			List<Usuario> usuarios = use.list();
			
			for (Usuario u : usuarios) {
				EntityManagerUtil.getEMIntance().detach(u);
				u.setSenha(null);
			}

			return usuarios;
	}

	public Object editarUsuario(Usuario usu) throws NoSuchAlgorithmException {
		
		UsuarioJPA user = new UsuarioJPA();		
		Object obj = null;
		
		List<Usuario> retorno = user.buscarPorEmail(usu);
		if(retorno.size() > 0){
			for (Usuario u : retorno) {
				if(u.getId() != usu.getId()){
					return obj;
				}					
			}				
		}
		Crypt crypt = new Crypt();
		usu.setSenha(crypt.md5(crypt.bs64d(usu.getSenha())));
		user.editarUsuario(usu);
		
		List<Usuario> usuarios = user.list();
		
		for (Usuario u : usuarios) {
			EntityManagerUtil.getEMIntance().detach(u);
			u.setSenha(null);
		}

		return usuarios;
	}
	
}
