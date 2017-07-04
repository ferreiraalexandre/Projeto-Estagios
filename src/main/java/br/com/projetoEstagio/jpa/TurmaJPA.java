package br.com.projetoEstagio.jpa;

import java.util.List;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.interfaces.TurmaInterface;

public class TurmaJPA  extends JPAAbstract<Turma, Long> implements TurmaInterface {

	public Turma addTurma(Turma tur) {
		return this.add(tur);
	}

	public List<Turma> list(){
		return this.list("");
		
	}
	public void deleteTurma(long id){
		this.remove(id);
	}

	public Turma editarTurma(Turma usu) {
		return this.edit(usu);
	}
	
	public Turma findById(Long id) {
		return this.getObject("SELECT T FROM "+ this.getEntityName() +" T WHERE T.id = '"+ id +"'");
	}

	public List<Turma> buscarPorId(Long id) {
		//System.out.println("****** SELECT U FROM "+ this.getEntityName() +" U WHERE U.curso.id = '"+ id +"'  **********************");
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.curso.id = '"+ id +"'");
	}

}