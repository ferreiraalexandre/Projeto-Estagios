package br.com.projetoEstagio.service;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.jpa.UnidadeEnsinoJPA;

public class UnidadeEnsinoService {

	public Object addUnidadeEnsino(UnidadeEnsino uni) {
		UnidadeEnsinoJPA user = new UnidadeEnsinoJPA();
		user.addUnidadeEnsino(uni);
		return user;
	}

}
