package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.CursoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;
import br.com.projetoEstagio.pojo.TurmaPojo;

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
	
	public TurmaPojo listSelect() throws Exception {
		TurmaPojo turmaPojo = new TurmaPojo();
		CursoJPA curso = new CursoJPA();
		UsuarioJPA usuario = new UsuarioJPA();
		
		turmaPojo.setCurso(curso.list());
		turmaPojo.setUsuario(usuario.listCoordenadores());
	
		return turmaPojo;
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
		TurmaJPA user = new TurmaJPA();
		user.editarTurma(tur);
		return user.list();
	}
	
}
