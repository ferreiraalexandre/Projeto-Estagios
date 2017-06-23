package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.interfaces.InstituicaoInterface;

public class InstituicaoJPA  extends JPAAbstract<Instituicao, Long> implements InstituicaoInterface {

	public Instituicao addInstituicao(Instituicao instituicao) {
		return this.add(instituicao);
	}
	
	public List<Instituicao> list(){
		return this.list("");
	}
	public void deleteInstituicao(long id){
		this.remove(id);
	}
	
	public Instituicao editarInstituicao(Instituicao instituicao) {
		return this.edit(instituicao);
	}

	
}