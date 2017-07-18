package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Estagio;

public interface EstagioInterface {

	public List<Estagio> list();
	
	public Estagio addEstagio(Estagio estagio);
	
	public Estagio editarEstagio(Estagio estagio);

	public void deleteEstagio(long id);
}
