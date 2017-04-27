package br.com.projetoEstagio.jpa;


import java.util.List;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.interfaces.UnidadeEnsinoInterface;

public class UnidadeEnsinoJPA  extends JPAAbstract<UnidadeEnsino, Long> implements UnidadeEnsinoInterface {

	public UnidadeEnsino addUnidadeEnsino(UnidadeEnsino uni) {
		return this.add(uni);
	}
	
	public List<UnidadeEnsino> list(){
		return this.list("");
	}


}