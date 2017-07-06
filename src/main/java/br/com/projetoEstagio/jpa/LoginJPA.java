package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.interfaces.LoginInterface;

public class LoginJPA  extends JPAAbstract<Usuario, Integer> implements LoginInterface {

		public Usuario getObject(String email) {
			return this.getObject("SELECT U FROM "+ this.getEntityName() +" U WHERE U.email = '"+ email +"'");
		}	
	}