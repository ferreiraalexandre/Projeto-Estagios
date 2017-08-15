package br.com.projetoEstagio.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.interfaces.EstagioInterface;
import br.com.projetoEstagio.pojo.RelatorioPojo;

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
	
	public List<Estagio> filtrar(Date dataInicio, Date dataFim, Long cursoId, Long empresaId){
	
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String inicio = formato.format(dataInicio);
		String fim = formato.format(dataFim);
		
		String hql = "SELECT E FROM "+ this.getEntityName() +" E WHERE E.dataFim BETWEEN '" + inicio + "'" + "AND '" + fim +"'";
		if(cursoId != null){
			hql += "and E.curso.id = '" + cursoId + "'";
		}
		if(empresaId != null){
			hql += "and E.empresa.id = '" + empresaId + "'";
		}

		return this.list(hql);
	

	}	

	public List<Estagio> estagioVencendo(){
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, +10);

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String inicio = formato.format(new Date());
		String fim = formato.format(c.getTime());
		
		String hql = "SELECT E FROM "+ this.getEntityName() +" E WHERE E.dataFim BETWEEN '" + inicio + "'" + "AND '" + fim +"'";

		return this.list(hql);
	

	}

	public List<Object> buscarEmpresaComEstagiario() {
		
		String hql = "SELECT new " + RelatorioPojo.class.getName() + " (em.nome, COUNT(*)as total)"
				+ " FROM " + Empresa.class.getSimpleName() + " em"
				+ " LEFT JOIN " + Estagio.class.getSimpleName() + " es"
				+ " WHERE em.id = es.empresaId group by nome";
		
//		String hql = "SELECT new " + RelatorioPojo.class.getName() + " (es.empresa.nome, COUNT(*)as total)"
//				+ " FROM " + Estagio.class.getSimpleName() + " es"
//				+ " WHERE es.empresa = es.empresa.id group by nome";

				
		return this.listObject(hql);
	}	

}
