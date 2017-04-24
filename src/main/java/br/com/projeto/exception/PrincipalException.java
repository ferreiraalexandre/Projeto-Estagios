package br.com.projeto.exception;

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
}
