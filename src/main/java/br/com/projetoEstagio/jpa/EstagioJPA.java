package br.com.projetoEstagio.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.interfaces.EstagioInterface;

public class EstagioJPA extends JPAAbstract<Estagio, Long> implements EstagioInterface {
	
	public List<Estagio> list(){
		return this.list("");
		
	}
	
	public Estagio addEstagio(Estagio estagio) {
		return this.add(estagio);
	}

	public Estagio editarEstagio(Estagio estagio) {
		return this.edit(estagio);
	}
	
	public void deleteEstagio(long id){
		this.remove(id);
	}
	
	public List<Estagio> filtrar(Date dataInicio, Date dataFim, Long turmaId) {
		String hql = "SELECT E FROM "+ this.getEntityName() +" E WHERE E.dataFim BETWEEN :dataInicio AND :dataFim";
		

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("dataInicio", dataInicio);
		params.put("dataFim", dataFim);
		return findByQuery(hql, params);
		

	

	}	

}
