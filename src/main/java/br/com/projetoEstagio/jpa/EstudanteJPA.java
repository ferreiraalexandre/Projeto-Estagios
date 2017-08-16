package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.interfaces.EstudanteInterface;

public class EstudanteJPA extends JPAAbstract<Estudante, Long> implements EstudanteInterface {
	
	public List<Estudante> list(){
		return this.list("");
		
	}
	
	public Estudante addEstudante(Estudante estudante) {
		return this.add(estudante);
	}

	public Estudante editarEstudante(Estudante estudante) {
		return this.edit(estudante);
	}

	public List<Estudante> buscarPorTurma(Long id) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.turma.id = '"+ id +"'");
	}

}
