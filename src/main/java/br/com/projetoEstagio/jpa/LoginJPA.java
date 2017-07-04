package br.com.projetoEstagio.jpa;

import java.util.List;

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.interfaces.CursoInterface;
import br.com.projetoEstagio.interfaces.LoginInterface;

public class LoginJPA  extends JPAAbstract<Usuario, Integer> implements LoginInterface {

		@Override
		public int getEmailAmount(String email) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Usuario getObject(String email, String password) {
			return this.getObject("SELECT u FROM "+this.getEntityName()+" u WHERE u.email='"+email+"' AND u.password='"+password+"'");
		}	
	}