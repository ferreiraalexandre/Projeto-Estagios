package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;
import br.com.projetoEstagio.restUtil.RestResponse;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.CursoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;


public class CursoService {

	public Object addCurso(Curso cur) {
		CursoJPA curso = new CursoJPA();
		curso.addCurso(cur);
		
		return curso.list();
		
	}
	
	public List<Curso> listCurso() throws Exception {
		CursoJPA curso = new CursoJPA();
		return curso.list();
	}
	
	public Object deleteCurso(JSONArray cur, RestResponse response) throws Exception{
		CursoJPA curso = new CursoJPA();
		TurmaJPA tur = new TurmaJPA();
		
		List<Turma> turmaEmUso = new ArrayList<Turma>();
			
			if(cur != null && cur.length() > 0){
				for (int i = 0; i < cur.length(); i++) {
					List<Turma> turma = tur.buscarPorId(cur.getLong(i));
					
					if(turma.size() > 0){
						turmaEmUso.add(turma.get(0));	
					}else{
						curso.deleteCurso(cur.getLong(i));												
					}
				}
			}
			
			if(turmaEmUso.size() > 0){
				String nomeTurma = "";
				for (Turma turma : turmaEmUso) {
					if(!nomeTurma.contains(turma.getCurso().getNome())){		
						nomeTurma += turma.getCurso().getNome() + "  ";						
					}
				}
				response.setDescription(nomeTurma.replace("  ", "; "));
			}			
	
			List<Curso> cursos = curso.list();
			
			return cursos;
	}
	
	public Object editarCurso(Curso cur) {
		CursoJPA curso = new CursoJPA();
		curso.editarCurso(cur);
		return curso.list();
	}
}
