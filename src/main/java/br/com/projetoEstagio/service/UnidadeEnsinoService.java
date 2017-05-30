package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.UnidadeEnsinoJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;

public class UnidadeEnsinoService {

	public Object addUnidadeEnsino(UnidadeEnsino uni) {
		UnidadeEnsinoJPA unidade = new UnidadeEnsinoJPA();
		unidade.addUnidadeEnsino(uni);
		
		return unidade.list();
		
	}
	
	public List<UnidadeEnsino> listUnidade() throws Exception {
		UnidadeEnsinoJPA listUnidade = new UnidadeEnsinoJPA();
		return listUnidade.list();
	}
	
	public Object deleteUnidade(JSONArray unid) throws Exception{
		UnidadeEnsinoJPA uni = new UnidadeEnsinoJPA();
		UsuarioJPA use = new UsuarioJPA();
		
		List<String> usuarioEmUso = new ArrayList<String>();
			
			if(unid != null && unid.length() > 0){
				for (int i = 0; i < unid.length(); i++) {
					Usuario usuario = use.buscarPorId(unid.getLong(i));
					if(usuario == null){
						uni.deleteUnidadeEnsino(unid.getLong(i));						
					}else{
						usuarioEmUso.add(usuario.getNome());	
					}
				}
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
