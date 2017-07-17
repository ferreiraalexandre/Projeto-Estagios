package br.com.projetoEstagio.service;

import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.jpa.EstudanteJPA;;


public class EstudanteService {
	
	public Estudante editarEstudante(Estudante estudante) {
		EstudanteJPA jpa = new EstudanteJPA();
		return jpa.editarEstudante(estudante);
	}
}
