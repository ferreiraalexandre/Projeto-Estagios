package br.com.projetoEstagio.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * 
 * Interface basica para crud do padrao DAO.
 * 
 *
 * @param <E>
 *            Entidade
 * @param <ID>
 *            Tipo da chave primaria
 */
public interface CrudDAO<E, ID> {

	/**
	 * 
	 * Metodo que retorna um objeto que e o registro que consta na entidade.
	 * 
	 * @param id
	 *            Id do registro da entidade.
	 * @return E objeto da entidade.
	 */
	public E getObject(ID id);

	/**
	 * 
	 * Metodo para puxar o objeto tudo em uma trasacao so.
	 * 
	 * @param id do recurso
	 * @param em transacao
	 * @return objeto do recurso
	 */
	public E getObject(ID id, EntityManager em);
	
	/**
	 * 
	 * Metodo que insere determinada instancia de uma entidade no banco de dados
	 * 
	 * @param e
	 *            instancia da entidade
	 */
	public E add(E e);

	/**
	 * 
	 * Metodo para multiplos cadastros no banco em uma unica trasacao.
	 * 
	 * @param e
	 *            entidade
	 * @param em
	 *            transacao do banco.
	 * @return 
	 */
	public E add(E e, EntityManager em);

	/**
	 * 
	 * Metodo que realiza a edicao da instancia de uma entidade no banco de
	 * dados.
	 * 
	 * @param e
	 *            instancia da entidade
	 */
	public E edit(E e);

	/**
	 * 
	 * Metodo para multiplas edicoes no banco em uma unica trasacao.
	 * 
	 * @param e
	 *            entidade
	 * @param em
	 *            transacao do banco.
	 */
	public E edit(E e, EntityManager em);

	/**
	 * 
	 * Metodo que remove a instancia da entidade no banco de dados.
	 * 
	 * @param id
	 *            instancia do tipo da chave primaria do banco de dados.
	 * @return 
	 */
	public Boolean remove(ID id);

	/**
	 * 
	 * Metodo para fazer a listagem dos registros que se encontram na entidade.
	 * 
	 * @return List<E> retorna um array list de registros da entidade.
	 */
	public List<E> list();
}
