package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Estudante;

public interface EstudanteInterface {

	
	public List<Estudante> list();
	
	public Estudante addEstudante(Estudante estudante);
	
	public Estudante editarEstudante(Estudante estudante);
}
