package br.com.projetoEstagio.connection;

import javax.persistence.EntityManager;

public class JPAConnection {

	protected EntityManager getEntityManager(){
		return EntityManagerUtil.getEMIntance();
	}
}
