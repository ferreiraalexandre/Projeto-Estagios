package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Instituicao;

public interface InstituicaoInterface {
	
	public Instituicao addInstituicao(Instituicao instituicao);
	
	public List<Instituicao> list();
	
	public void deleteInstituicao(long id);
	
	public Instituicao editarInstituicao(Instituicao instituicao);
	
	public List<Instituicao> validate(Instituicao inst);
	
	public Instituicao findById(Long id);

}
