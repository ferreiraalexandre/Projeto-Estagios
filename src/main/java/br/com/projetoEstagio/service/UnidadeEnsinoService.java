package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UnidadeEnsinoJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;
import br.com.projetoEstagio.restUtil.RestResponse;

public class UnidadeEnsinoService {

	public Object addUnidadeEnsino(UnidadeEnsino uni) {
		UnidadeEnsinoJPA unidade = new UnidadeEnsinoJPA();
		unidade.addUnidadeEnsino(uni);		
		return unidade.list();		
	}
	
	public List<UnidadeEnsino> validar(UnidadeEnsino uni) throws Exception {
		UnidadeEnsinoJPA jpa = new UnidadeEnsinoJPA();
		return jpa.validate(uni);
	}
	
	public List<UnidadeEnsino> listUnidade() throws Exception {
		UnidadeEnsinoJPA listUnidade = new UnidadeEnsinoJPA();
		return listUnidade.list();
	}
	
	public Object deleteUnidade(JSONArray unid, RestResponse response) throws Exception{
		UnidadeEnsinoJPA uni = new UnidadeEnsinoJPA();
		UsuarioJPA use = new UsuarioJPA();
		
		List<Usuario> usuarioEmUso = new ArrayList<Usuario>();
			
			if(unid != null && unid.length() > 0){
				for (int i = 0; i < unid.length(); i++) {
					List<Usuario> usuario = use.buscarPorId(unid.getLong(i));
					
					if(usuario.size() > 0){
						usuarioEmUso.add(usuario.get(0));	
					}else{
						uni.deleteUnidadeEnsino(unid.getLong(i));												
					}
				}
			}
			
			if(usuarioEmUso.size() > 0){
				String nomeUsuario = "";
				for (Usuario usuario : usuarioEmUso) {
					if(!nomeUsuario.contains(usuario.getUnidadeEnsino().getNome())){		
						nomeUsuario += usuario.getUnidadeEnsino().getNome() + "  ";						
					}
				}
				response.setDescription(nomeUsuario.replace("  ", "; "));
			}			
	
			List<UnidadeEnsino> unidades = uni.list();
			
			return unidades;
	}
	
	public Object editarUnidade(UnidadeEnsino uni) {
		UnidadeEnsinoJPA unid = new UnidadeEnsinoJPA();
		unid.editarUnidade(uni);
		return unid.list();
	}
}
