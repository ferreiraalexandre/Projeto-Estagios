package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.EstagioJPA;
import br.com.projetoEstagio.jpa.EstudanteJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.restUtil.RestResponse;

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
	
	public Object deleteTurma(JSONArray turmas, RestResponse response) throws Exception{
		TurmaJPA turma = new TurmaJPA();
		EstagioJPA estagio = new EstagioJPA();
		EstudanteJPA estudante = new EstudanteJPA();
		
		List<Turma> turmaEmUso = new ArrayList<Turma>();
			
			if(turmas != null && turmas.length() > 0){
				for (int i = 0; i < turmas.length(); i++) {
					List<Estagio> estagios = estagio.buscarPorTurma(turmas.getLong(i));
					List<Estudante> estudantes = estudante.buscarPorTurma(turmas.getLong(i));
					
					if(estagios.size() > 0 || estudantes.size() > 0){
						if(estagios.size() > 0){
							turmaEmUso.add(estagios.get(0).getTurma());
						}
						if(estudantes.size() > 0){
							turmaEmUso.add(estudantes.get(0).getTurma());
						}
					}else{
						turma.deleteTurma(turmas.getLong(i));												
					}
				}
			}
			
			if(turmaEmUso.size() > 0){
				String nomeTurma = "";
				for (Turma tur : turmaEmUso) {
					if(!nomeTurma.contains(tur.getNome())){		
						nomeTurma += tur.getNome() + " - ";						
					}
				}
				response.setDescription(nomeTurma.replace(" * ", ", "));
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
