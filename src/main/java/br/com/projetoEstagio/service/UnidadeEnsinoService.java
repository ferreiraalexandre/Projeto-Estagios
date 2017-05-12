package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.jpa.UnidadeEnsinoJPA;

public class UnidadeEnsinoService {

	public Object addUnidadeEnsino(UnidadeEnsino uni) {
		UnidadeEnsinoJPA user = new UnidadeEnsinoJPA();
		user.addUnidadeEnsino(uni);
		return user.list();
	
	}
	
	public List<UnidadeEnsino> listUnidade() throws Exception {
		UnidadeEnsinoJPA listUnidade = new UnidadeEnsinoJPA();
		return listUnidade.list();
	}
	
	public Object deleteUnidade(JSONArray unid) throws Exception{
		UnidadeEnsinoJPA uni = new UnidadeEnsinoJPA();
			
			if(unid != null && unid.length() > 0){
				for (int i = 0; i < unid.length(); i++) {
					uni.deleteUnidadeEnsino(unid.getLong(i));
				}
			}
			
			return uni.list();
	}
	
	public Object editarUnidade(UnidadeEnsino uni) {
		UnidadeEnsinoJPA unid = new UnidadeEnsinoJPA();
		unid.editarUnidade(uni);
		return unid;
	}

}
