package br.com.projetoEstagio.jpa;


import java.util.List;

//import br.com.projetoEstagio.entity.Curso;
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

	public Turma editarTurma(Turma tur) {
		return this.edit(tur);
	}
	
//	public Usuario buscarPorId(Long id) {
//		return this.getObject("SELECT U FROM "+ this.getEntityName() +" U WHERE U.unidadeEnsino.id = '"+ id +"'");
//	}

}