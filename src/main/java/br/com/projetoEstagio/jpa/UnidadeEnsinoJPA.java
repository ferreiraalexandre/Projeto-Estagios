package br.com.projetoEstagio.jpa;


import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.interfaces.UnidadeEnsinoInterface;

public class UnidadeEnsinoJPA  extends JPAAbstract<UnidadeEnsino, Long> implements UnidadeEnsinoInterface {

	public UnidadeEnsino addUnidadeEnsino(UnidadeEnsino uni) {
		return this.add(uni);
	}


}