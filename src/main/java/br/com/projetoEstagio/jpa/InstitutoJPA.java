package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Instituto;
import br.com.projetoEstagio.interfaces.InstitutoInterface;




public class InstitutoJPA  extends JPAAbstract<Instituto, Long> implements InstitutoInterface {

	public Instituto addInstituto(Instituto instituto) {
		return this.add(instituto);
	}
	
	public List<Instituto> list(){
		return this.list("");
	}
	public void deleteInstituto(long id){
		this.remove(id);
	}
	
	public Instituto editarInstituto(Instituto instituto) {
		return this.edit(instituto);
	}

	
}