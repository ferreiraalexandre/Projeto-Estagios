package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.interfaces.EmpresaInterface;

public class EmpresaJPA  extends JPAAbstract<Empresa, Long> implements EmpresaInterface {

	public Empresa addEmpresa(Empresa emp) {
		return this.add(emp);
	}

	public List<Empresa> list(){
		return this.list("");
		
	}
	
	public Empresa editarEmpresa(Empresa emp) {
		return this.edit(emp);
	}
	
	public void deleteEmpresa(long id){
		this.remove(id);
	}
	
	public Empresa findById(Long id) {
		return this.getObject("SELECT E FROM "+ this.getEntityName() +" E WHERE E.id = '"+ id +"'");
	}

}