package br.com.projetoEstagio.restUtil;

import br.com.projeto.exception.PrincipalException;


public class RestResponse {

	private String message;
	private String description;
	private Object data;
	
	public RestResponse(Exception e){
		
		if(e instanceof PrincipalException){
			this.RestResponseMainEx((PrincipalException) e);
		}else{
			this.RestResponseEx(e);
		}
	}
	
	public RestResponse(String message, String description, Object data) {
		super();
		this.message = message;
		this.description = description;
		this.data = data;
	}

	private void RestResponseEx(Exception e){
		e.printStackTrace();
		this.message = "Erro no servidor";
		this.description = "Gentileza entrar em contato com o desenvolvedor.";
		this.data = null;
	}
	
	private void RestResponseMainEx(PrincipalException e){
		ObjectException obj = e.getObject();
		
		this.message = obj.getMessage();
		this.description = obj.getDescription();
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
