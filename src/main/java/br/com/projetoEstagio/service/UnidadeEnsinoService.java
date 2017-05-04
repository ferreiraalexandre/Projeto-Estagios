package br.com.projetoEstagio.service;

import java.util.List;

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
	
	public void deleteUnidade(List unid) throws Exception{
		UnidadeEnsinoJPA uni = new UnidadeEnsinoJPA();
		for (int i = 0; i < unid.size(); i++) {
			Long ids = (long) unid.get(i);
			uni.deleteUnidadeEnsino(ids);
		}
		
		
	}

}
