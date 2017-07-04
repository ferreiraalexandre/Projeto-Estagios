package br.com.projetoEstagio.interfaces;


import br.com.projetoEstagio.entity.Usuario;

	public interface LoginInterface {

		/**
		 * 
		 * Metodo que realiza uma consulta para quais os usuarios possuem aquele
		 * e-mail passado por parametro.
		 * 
		 * @param email
		 *            a ser procurado.
		 * @return quantidade de usuarios que possuem aquele email.
		 */
		public int getEmailAmount(String email);
		public Usuario getObject(String email, String password);
	
	

}
