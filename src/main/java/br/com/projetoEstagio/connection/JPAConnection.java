package br.com.projetoEstagio.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {

	protected EntityManager getEntityManager(){
		return EntityManagerUtil.getEMIntance();
	}
}
