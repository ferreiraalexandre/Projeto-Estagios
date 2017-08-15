package br.com.projetoEstagio.pojo;

public class RelatorioPojo {
	private String nome;
	private long total;
	
	public RelatorioPojo(String nome, long total){
		super();
		this.nome = nome;
		this.total = total;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

}
