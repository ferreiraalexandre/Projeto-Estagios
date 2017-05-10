package br.com.projetoEstagio.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.projetoEstagio.connection.JPAConnection;
import br.com.projetoEstagio.interfaces.JPAAbstractInterface;

import javax.persistence.Query;



/**
 * 
 * Classe abstrata que devera ser herdada pelas outras classes JPA DAO.
 * 
 *
 * @param <E>
 *            entity
 * @param <ID>
 *            Tipo da chave primaria
 */
public class JPAAbstract<E, ID> extends JPAConnection implements JPAAbstractInterface<E, ID> {

	/**
	 * Atributo que armazena o .class da entity.
	 */
	private Class<E> entity;
	
	@SuppressWarnings("unchecked")
	public JPAAbstract() {

		@SuppressWarnings("rawtypes")
		Class<? extends JPAAbstract> realClass = getClass();

		ParameterizedType superclass = (ParameterizedType) realClass
				.getGenericSuperclass();

		this.entity = (Class<E>) superclass.getActualTypeArguments()[0];
	}

	/**
	 * 
	 * Metodo responsavel por retornar o nome da entity.
	 * 
	 * @return uma string com o nome da entity.
	 */
	protected String getEntityName() {

		String nomePadrao = this.entity.getSimpleName();

		Entity annotation = this.entity.getAnnotation(Entity.class);

		if (annotation != null && !annotation.name().isEmpty()) {
			return annotation.name();
		}

		return nomePadrao;
	}
	
	/**
	 * 
	 * Implementacao do metodo que retorna um objeto que e o registro que consta
	 * na entity.
	 * 
	 * @param id
	 *            Id do registro da entity.
	 * @return E objeto da entity. 
	 */
	public E getObject(ID id){

		EntityManager em = null;

		em = getEntityManager();
		
		E e = em.find(this.entity, id);

		this.closeConection();
		
		return e;
	}

	public E getObject(ID id, EntityManager em){
		
		if(em != null && em.getTransaction().isActive()){
			return em.find(this.entity, id);
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * Metodo que retorna um objeto que e o registro que consta
	 * na entity.
	 * 
	 * @param jpql
	 *            query sql.
	 * @return E objeto da entity.
	 */
	protected E getObject(String jpql){
		
		if(jpql == null || jpql.isEmpty()){
			jpql = "SELECT E FROM " + this.getEntityName() + " E";
		}

		EntityManager em = getEntityManager();

		TypedQuery<E> sql = em.createQuery(jpql, this.entity);
		
		E e = sql.getSingleResult();
		
		this.closeConection();
		
		return e;
	}
	
	/**
	 * 
	 * Implementacao do metodo que insere determinada instancia de uma entity
	 * no banco de dados
	 * 
	 * @param e
	 *            instancia da entity
	 */
	public E add(E e){

		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		em.persist(e);

		em.getTransaction().commit();
		
		em.close();

		this.closeConection();
		
		return e;
	}
	
	/**
	 * 
	 * Implementacao do metodo para multiplos cadastros no banco em uma unica trasacao.
	 * 
	 * @param e
	 *            entidade
	 * @param em
	 *            transacao do banco.
	 */
	public E add(E e, EntityManager em){
		
		if(em != null && em.getTransaction().isActive()){
			em.persist(e);
			return e;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * Implementacao do metodo que realiza a edicao da instancia de uma entity
	 * no banco de dados.
	 * 
	 * @param e
	 *            instancia da entity
	 */
	public E edit(E e){

		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		em.merge(e);

		em.getTransaction().commit();

		em.close();

		this.closeConection();
		
		return e;
	}
	
	/**
	 * 
	 * Metodo para multiplas edicoes no banco em uma unica trasacao.
	 * 
	 * @param e
	 *            entidade
	 * @param em
	 *            transacao do banco.
	 */
	public E edit(E e, EntityManager em){
		
		if(em != null && em.getTransaction().isActive()){
			em.merge(e);
		}
		
		return e;
	}
	
	/**
	 * 
	 * Implementacao do metodo que remove a instancia da entity no banco de
	 * dados.
	 * 
	 * @param id
	 *            instancia do tipo da chave primaria do banco de dados.
	 */
	public Boolean remove(ID id){

		E e = this.getObject(id);

		EntityManager em = getEntityManager();

		em.getTransaction().begin();

		e = em.merge(e);

		em.remove(e);

		em.getTransaction().commit();

		em.close();

		this.closeConection();
		
		return true;
	}

	/**
	 * 
	 * Implementacao do metodo para fazer a listagem dos registros que se
	 * encontram na entity.
	 * 
	 * @return List<E> retorna um array list de registros da entity.
	 */
	public List<E> list(){
		return this.list("");
	}

	/**
	 * 
	 * Metodo para fazer a listagem dos registros que se
	 * encontram na entity.
	 * 
	 * @param jpql
	 *            query sql.
	 * @return List<E> retorna um array list de registros da entity.
	 */
	protected List<E> list(String jpql){

		if(jpql == null || jpql.isEmpty()){
			jpql = "SELECT E FROM " + this.getEntityName() + " E";
		}
		
		EntityManager em = getEntityManager();

		TypedQuery<E> sql = em.createQuery(jpql, this.entity);
		
		List<E> listentity = sql.getResultList();

		this.closeConection();

		return listentity;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> listNativeQuery(String sql){
		
		if(sql == null || sql.isEmpty()){
			return null;
		}
		
		EntityManager em = getEntityManager();
		
		Query query = em.createNativeQuery(sql, this.entity);
		
		List<E> list = new ArrayList<E>();
		
		list = query.getResultList();
		
		this.closeConection();
		
		return list;
	}
}
