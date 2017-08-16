package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.CursoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.restUtil.RestResponse;


public class CursoService {

	public Object addCurso(Curso cur) {
		CursoJPA curso = new CursoJPA();		
		Object obj = null;
		
		List<Curso> retorno = curso.buscarPorNome(cur);
		if(retorno.size() > 0){
			return obj;
		}else{
			curso.addCurso(cur);
			return curso.list();
			
		}
	}
	
	public List<Curso> validar(Curso cur) throws Exception {
		CursoJPA jpa = new CursoJPA();
		return jpa.buscarPorNome(cur);
	}
	
	public List<Curso> listCurso() throws Exception {
		CursoJPA curso = new CursoJPA();
		return curso.list();
	}
	
	public Object deleteCurso(JSONArray cur, RestResponse response) throws Exception{
		CursoJPA curso = new CursoJPA();
		TurmaJPA tur = new TurmaJPA();
		
		List<Curso> cursoEmUso = new ArrayList<Curso>();
			
			if(cur != null && cur.length() > 0){
				for (int i = 0; i < cur.length(); i++) {
					List<Turma> turma = tur.buscarPorCurso(cur.getLong(i));
					
					if(turma.size() > 0){
						cursoEmUso.add(turma.get(0).getCurso());	
					}else{
						curso.deleteCurso(cur.getLong(i));												
					}
				}
			}
			
			if(cursoEmUso.size() > 0){
				String nomeCurso = "";
				for (Curso c : cursoEmUso) {
					if(!nomeCurso.contains(c.getNome())){		
						nomeCurso += c.getNome() + " - ";						
					}
				}
				response.setDescription(nomeCurso.replace(" * ", "; "));
			}
			return curso.list();
	}
	
	public Object editarCurso(Curso cur) {
		CursoJPA curso = new CursoJPA();
		Object obj = null;
		
		List<Curso> retorno = curso.buscarPorNome(cur);
		if(retorno.size() > 0){
			for (Curso u : retorno) {
				if(u.getId() != cur.getId()){
					return obj;
				}					
			}				
		}
		curso.editarCurso(cur);
		return curso.list();
	}
}



















