package br.com.projetoEstagio.pojo;

import java.util.List;

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.entity.Usuario;

public class TurmaPojo {
	
	private List<Curso> curso;
	private List<Usuario> usuario;
	
	public TurmaPojo() {
		super();
	}

	public TurmaPojo(List<Curso> curso, List<Usuario> usuario) {
		super();
		this.curso = curso;
		this.usuario = usuario;
	}

	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}
	
	

}
