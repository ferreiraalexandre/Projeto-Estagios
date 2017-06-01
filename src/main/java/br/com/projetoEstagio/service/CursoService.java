package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.jpa.CursoJPA;



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
	
	public Object deleteCurso(JSONArray cur) throws Exception{
		CursoJPA curso = new CursoJPA();
		//UsuarioJPA use = new UsuarioJPA();
		
//		List<String> usuarioEmUso = new ArrayList<String>();
//			
//			if(unid != null && unid.length() > 0){
//				for (int i = 0; i < unid.length(); i++) {
//					Usuario usuario = use.buscarPorId(unid.getLong(i));
//					if(usuario == null){
//						uni.deleteUnidadeEnsino(unid.getLong(i));						
//					}else{
//						usuarioEmUso.add(usuario.getNome());	
//					}
//				}
//			}
			
			List<Curso> cursos = curso.list();
			
			return cursos;
	}
	
	public Object editarCurso(Curso cur) {
		CursoJPA curso = new CursoJPA();
		curso.editarCurso(cur);
		return curso.list();
	}
}
