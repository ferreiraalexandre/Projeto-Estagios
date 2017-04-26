package br.com.projetoEstagio.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("projetoEstagio");

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}