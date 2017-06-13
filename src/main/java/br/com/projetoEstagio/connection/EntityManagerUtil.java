package br.com.projetoEstagio.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerUtil  emUtil= null;
	private static EntityManagerFactory conection;
	private static EntityManager em;
	
	public static EntityManager getEMIntance(){
		if(emUtil == null){
			emUtil = new EntityManagerUtil();
			conection = Persistence.createEntityManagerFactory("projetoEstagio");
			em = conection.createEntityManager();
		}
		
		return em;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		
		em.close();
		conection.close();
	}
	
}
