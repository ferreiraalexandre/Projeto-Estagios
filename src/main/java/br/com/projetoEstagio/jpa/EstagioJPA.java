package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.interfaces.EstagioInterface;

public class EstagioJPA extends JPAAbstract<Estagio, Long> implements EstagioInterface {
	
	public List<Estagio> list(){
		return this.list("");
		
	}
	
	public Estagio addEstagio(Estagio estagio) {
		return this.add(estagio);
	}



}
