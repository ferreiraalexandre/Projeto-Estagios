package br.com.projetoEstagio.service;

import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.jpa.EstudanteJPA;;


public class EstudanteService {
	
	public Object editarEstudante(Estudante estudante) {
		EstudanteJPA jpa = new EstudanteJPA();
		jpa.editarEstudante(estudante);
		return jpa.list();
	}
}
