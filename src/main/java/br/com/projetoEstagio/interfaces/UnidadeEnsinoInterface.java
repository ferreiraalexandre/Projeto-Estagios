package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.UnidadeEnsino;

public interface UnidadeEnsinoInterface {
	
	public UnidadeEnsino addUnidadeEnsino(UnidadeEnsino uni);
	
	public List<UnidadeEnsino> list();
	
	public void deleteUnidadeEnsino(long id);
	
	public UnidadeEnsino editarUnidade(UnidadeEnsino uni);
	
	public List<UnidadeEnsino> buscarPorNome(UnidadeEnsino user);

}
