package br.com.projetoEstagio.jpa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.interfaces.EstagioInterface;
import br.com.projetoEstagio.pojo.RelatorioPojo;

@Named
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
				
		String hql = "SELECT new " + RelatorioPojo.class.getName() + " (es.empresa.nome, COUNT(*)as total)"
				+ " FROM " + Estagio.class.getSimpleName() + " es"
				+ " WHERE es.empresa = es.empresa.id group by nome";

		return this.listObject(hql);
	}	

	public List<Object> buscarEstagioComRescisao() {
		
		String hql = "SELECT new " + RelatorioPojo.class.getName() + " (COUNT(*)as totalEstagio, COUNT(es.dataRescisao)as total)"
				+ " FROM " + Estagio.class.getSimpleName() + " es";

		return this.listObject(hql);
	}	

	public List<Object> buscarTurmaComEstagiario() {
		
		String hql = "SELECT new " + RelatorioPojo.class.getName() + " (es.turma.nome, COUNT(*)as total)"
				+ " FROM " + Estagio.class.getSimpleName() + " es"
				+ " WHERE es.turma = es.turma.id group by nome";

		return this.listObject(hql);
	}	
	
	public List<Estagio> buscarPorTurma(Long id) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.turma.id = '"+ id +"'");
	}
	
	public List<Estagio> buscarPorInstituicao(Long id) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.instituicao.id = '"+ id +"'");
	}
	
	public List<Estagio> buscarPorEmpresa(Long id) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.empresa.id = '"+ id +"'");
	}

}
