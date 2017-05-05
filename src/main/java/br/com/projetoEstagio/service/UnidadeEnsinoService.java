package br.com.projetoEstagio.service;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.jpa.UnidadeEnsinoJPA;

public class UnidadeEnsinoService {

	public Object addUnidadeEnsino(UnidadeEnsino uni) {
		UnidadeEnsinoJPA user = new UnidadeEnsinoJPA();
		user.addUnidadeEnsino(uni);
		return user;
	}
	
	public List<UnidadeEnsino> listUnidade() throws Exception {
		UnidadeEnsinoJPA listUnidade = new UnidadeEnsinoJPA();
		return listUnidade.list();
	}
	
	public Boolean deleteUnidade(JSONArray id) throws Exception{
		UnidadeEnsinoJPA uni = new UnidadeEnsinoJPA();
		
			Boolean retorno = false;
			for (int i = 0; i < id.length(); i++) {
				retorno =	uni.remove(id.getLong(i));
			}
			
			return retorno;
		}
}


