package br.com.projetoEstagio.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public List<Estagio> filtrar(Date dataInicio, Date dataFim, Long turmaId){
	
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String inicio = formato.format(dataInicio);
		String fim = formato.format(dataFim);
		//BETWEEN
		String hql = "SELECT E FROM "+ this.getEntityName() +" E WHERE E.dataFim = '" + fim + "'";
		

		Map<String, Object> params = new HashMap<String, Object>();
		
//		params.put("dataInicio", dataInicio);
//		params.put("dataFim", dataFim);
//		return findByQuery(hql, params);
		List<Estagio> tt = this.list(hql);
		return tt;
	

	}	

}
