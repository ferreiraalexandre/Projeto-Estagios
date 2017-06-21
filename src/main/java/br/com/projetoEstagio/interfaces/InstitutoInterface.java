package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Instituto;

public interface InstitutoInterface {
	
	public Instituto addInstituto(Instituto instituto);
	
	public List<Instituto> list();
	
	public void deleteInstituto(long id);
	
	public Instituto editarInstituto(Instituto instituto);

}
