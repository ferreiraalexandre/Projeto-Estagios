package br.com.projetoEstagio.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.EstagioJPA;
import br.com.projetoEstagio.jpa.EstudanteJPA;
import br.com.projetoEstagio.jpa.InstituicaoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.pojo.EstagioPojo;

public class EstagioService {

	public List<Estagio> listEstagio() throws Exception {
		EstagioJPA listEstagio = new EstagioJPA();
		
		List<Estagio> result = listEstagio.list();
		if(result.size() > 0){
			Date dataAtual = new Date();
			Date dataVencimento = new Date();
			
			Calendar c = Calendar.getInstance();
			c.setTime(dataVencimento);
			c.add(Calendar.DATE, +10);
			
			for (Estagio estagio : result) {
				if(estagio.getDataFim().before(dataAtual) || estagio.getDataFim().before(c.getTime()) || estagio.getDataFim() == c.getTime()){
					System.out.println(estagio.getDataFim());
					
				}
			}
		}
		return result;
	}
	
	public EstagioPojo listSelect() throws Exception {
		EstagioPojo estagioPojo = new EstagioPojo();
		EmpresaJPA empresa = new EmpresaJPA();
		EstudanteJPA estudante = new EstudanteJPA();
		InstituicaoJPA instituicao = new InstituicaoJPA();
		TurmaJPA turma = new TurmaJPA();
		
		estagioPojo.setEmpresa(empresa.list());
		estagioPojo.setEstudante(estudante.list());
		estagioPojo.setInstituicao(instituicao.list());
		estagioPojo.setTurma(turma.list());
		
	
		return estagioPojo;
	}

	public Object addEstagio(Estagio estagio) {
		EstagioJPA estagioJPA = new EstagioJPA();
		estagioJPA.addEstagio(estagio);
		
		return  estagioJPA.list();

	}

	public Estudante addEstudante(Estudante estudante) {
		EstudanteJPA jpa = new EstudanteJPA();
		
		return  jpa.addEstudante(estudante);

	}
	
	public Estagio editarEstagio(Estagio estagio) {
		EstagioJPA jpa = new EstagioJPA();
		return jpa.editarEstagio(estagio);
	}
	
	public Object deleteEstagio(JSONArray estagio) throws Exception{
		EstagioJPA jpa = new EstagioJPA();
			
		if(estagio != null && estagio.length() > 0){
			for (int i = 0; i < estagio.length(); i++) {
				jpa.deleteEstagio(estagio.getLong(i));
			
			}
		}
		return jpa.list();
	}

	public List<Estagio> filtrarEstagio(Date dataInicio, Date dataFim, Long cursoId, Long empresaId) throws Exception {
		EstagioJPA jpa = new EstagioJPA();
		return jpa.filtrar(dataInicio, dataFim, cursoId, empresaId);
	}
	
	public List<Estagio> estagioVencendo() throws Exception {
		EstagioJPA jpa = new EstagioJPA();
		
		List<Estagio> result = jpa.estagioVencendo();

		return result;
	}
	
	public JSONObject relatorio() throws Exception{
		JSONObject json = new JSONObject();
		EstagioJPA jpa = new EstagioJPA();
		
		json.put("relatorio", jpa.buscarEmpresaComEstagiario());
		return json;
		
	}

}
