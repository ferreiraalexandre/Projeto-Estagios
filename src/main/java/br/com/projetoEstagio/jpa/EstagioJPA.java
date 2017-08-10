package br.com.projetoEstagio.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		
		String hql = "SELECT E FROM "+ this.getEntityName() +" E WHERE E.dataFim BETWEEN '" + inicio + "'" + "AND '" + fim +"'";
		if(turmaId != null){
			hql += "and E.turma.id = '" + turmaId + "'";
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

}
