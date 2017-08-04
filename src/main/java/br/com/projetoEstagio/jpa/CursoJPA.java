package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.interfaces.CursoInterface;

public class CursoJPA  extends JPAAbstract<Curso, Long> implements CursoInterface {

	public Curso addCurso(Curso cur) {
		return this.add(cur);
	}
	
	public List<Curso> list(){
		return this.list("");
	}
	public void deleteCurso(long id){
		this.remove(id);
	}
	
	public Curso editarCurso(Curso cur) {
		return this.edit(cur);
	}

	public List<Curso> validate(Curso cur) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.nome = '"+ cur.getNome() +"'"
				+ " or U.sigla = '"+ cur.getSigla() +"'");
	}

}