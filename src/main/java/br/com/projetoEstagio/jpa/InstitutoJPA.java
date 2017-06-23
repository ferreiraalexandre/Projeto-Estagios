package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.interfaces.InstitutoInterface;




public class InstitutoJPA  extends JPAAbstract<Instituicao, Long> implements InstitutoInterface {

	public Instituicao addInstituto(Instituicao instituto) {
		return this.add(instituto);
	}
	
	public List<Instituicao> list(){
		return this.list("");
	}
	public void deleteInstituto(long id){
		this.remove(id);
	}
	
	public Instituicao editarInstituto(Instituicao instituto) {
		return this.edit(instituto);
	}

	
}