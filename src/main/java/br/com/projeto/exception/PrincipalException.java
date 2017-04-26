package br.com.projeto.exception;

import br.com.projetoEstagio.rest.ObjectException;

public class PrincipalException extends Exception {

	private static final long serialVersionUID = 1L;

	public PrincipalException(String msg) {
		super(msg);
	}

	public PrincipalException(String msg,Throwable t) {
		super(msg, t);
	}

	public PrincipalException(Throwable t) {
		super("Erro de Conexão", t);
	}

	public ObjectException getObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
