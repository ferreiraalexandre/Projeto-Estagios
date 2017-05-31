package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.interfaces.EmpresaInterface;

public class EmpresaJPA  extends JPAAbstract<Empresa, Long> implements EmpresaInterface {

	/*public Empresa addEmpresa(Empresa usu) {
		return this.add(usu);
	}*/

	public List<Empresa> list(){
		return this.list("");
		
	}
	
	/*public void deleteEmpresa(long id){
		this.remove(id);
	}*/

	/*public Empresa editarUsuario(Empresa emp) {
		return this.edit(usu);
	}*/
	
	/*public Empresa buscarPorId(Long id) {
		return this.getObject("SELECT U FROM "+ this.getEntityName() +" U WHERE U.unidadeEnsino.id = '"+ id +"'");
	}*/

}