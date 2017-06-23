package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Instituicao;

public interface InstitutoInterface {
	
	public Instituicao addInstituto(Instituicao instituto);
	
	public List<Instituicao> list();
	
	public void deleteInstituto(long id);
	
	public Instituicao editarInstituto(Instituicao instituto);

}
