package br.com.projetoEstagio.jpa;


import java.util.List;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.interfaces.UsuarioInterface;

public class UsuarioJPA  extends JPAAbstract<Usuario, Long> implements UsuarioInterface {

	public Usuario addUsuario(Usuario usu) {
		return this.add(usu);
	}

	public List<Usuario> list(){
		return this.list("");
	
		
		
//		String hql = "SELECT a FROM " + AccountingOpenness.class.getSimpleName() + " a"
//				+ " INNER JOIN a.cashPosting c"
//				+ " LEFT JOIN c.financialOpenness f"
//				+ " WHERE (f.conciliated = :conciliated OR f.conciliated IS NULL) AND a.externalCode IN (:externalCodes)";

		
	}
	public void deleteUsuario(long id){
		this.remove(id);
	}

	public Usuario editarUsuario(Usuario usu) {
		return this.edit(usu);
	}
}