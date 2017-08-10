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
	
	public List<Instituicao> buscarPorNome(Instituicao inst) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.nome = '"+ inst.getNome() +"'"
				+ " or U.sigla = '"+ inst.getSigla() +"'");
	}

	public Instituicao findById(Long id) {
		return this.getObject("SELECT I FROM "+ this.getEntityName() +" I WHERE I.id = '"+ id +"'");
	}

	
}