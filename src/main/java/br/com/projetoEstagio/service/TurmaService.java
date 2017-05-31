package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.TurmaJPA;

public class TurmaService {

	public Object addTurma(Turma tur) {
		TurmaJPA turma = new TurmaJPA();
		turma.addTurma(tur);
		
		return  turma.list();

	}

	public List<Turma> listTurma() throws Exception {
		TurmaJPA listTurma = new TurmaJPA();
		return listTurma.list();
	}
	public Object deleteTurma(JSONArray tur) throws Exception{
		TurmaJPA turma = new TurmaJPA();
			
		if(tur != null && tur.length() > 0){
			for (int i = 0; i < tur.length(); i++) {
				turma.deleteTurma(tur.getLong(i));
				System.out.println(tur.getLong(i));
			}
		}
		return turma.list();
	}

	public Object editarTurma(Turma tur) {
		TurmaJPA turma = new TurmaJPA();
		turma.editarUsuario(tur);
		return turma.list();
	}
	
}
