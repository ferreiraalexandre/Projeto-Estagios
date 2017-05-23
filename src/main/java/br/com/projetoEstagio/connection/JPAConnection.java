package br.com.projetoEstagio.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {

	private static EntityManagerFactory conection;
	private static EntityManager em;
	
	private EntityManagerFactory connect(){
		
		try{
			if(conection != null && conection.isOpen()){
				return conection;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		conection = Persistence.createEntityManagerFactory("projetoEstagio");
		
		return conection;
	}
	
	protected EntityManager getEntityManager(){
		if(em == null || !em.isOpen()){
			em = connect().createEntityManager();
		}
		return em;
	}
	
	protected void closeConection(){
		
		if(em != null && em.isOpen()){
			em.close();
		}
	}
}
