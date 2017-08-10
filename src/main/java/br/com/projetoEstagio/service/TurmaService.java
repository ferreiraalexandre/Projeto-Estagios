package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.TurmaJPA;

public class TurmaService {

	public Object addTurma(Turma tur) {
		TurmaJPA turma = new TurmaJPA();									
		Object obj = null;
				
		List<Turma> retorno = turma.buscarSemId(tur);
		if(retorno.size() > 0){
			return obj;
		}else{
			obj = turma.addTurma(tur);			
			return turma.list();							
		}
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
		Object obj = null;
		
		List<Turma> retorno = turma.buscarSemId(tur);
		if(retorno.size() > 0){
			for (Turma u : retorno) {
				if(u.getId() != tur.getId()){
					return obj;
				}					
			}				
		}
		turma.editarTurma(tur);
		return turma.list();
		
	}
	
}
