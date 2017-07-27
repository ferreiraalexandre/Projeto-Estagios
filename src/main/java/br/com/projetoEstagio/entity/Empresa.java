package br.com.projetoEstagio.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
