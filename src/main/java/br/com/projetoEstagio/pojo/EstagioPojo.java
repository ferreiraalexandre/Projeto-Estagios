package br.com.projetoEstagio.pojo;

import java.util.List;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.entity.Turma;

public class EstagioPojo {

	private List<Empresa> empresa;

	private List<Instituicao> instituicao;

	private List<Turma> turma;
	
	private List<Estudante> estudante;
	
	public EstagioPojo() {
		super();
	}
	
	public EstagioPojo(List<Empresa> empresa, List<Instituicao> instituicao, List<Turma> turma, List<Estudante> estudante) {
		super();
		this.empresa = empresa;
		this.instituicao = instituicao;
		this.turma = turma;
		this.estudante = estudante;
	}
	
	public List<Empresa> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<Empresa> list) {
		this.empresa = list;
	}


	public List<Instituicao> getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(List<Instituicao> instituicao) {
		this.instituicao = instituicao;
	}

	public List<Turma> getTurma() {
		return turma;
	}

	public void setTurma(List<Turma> turma) {
		this.turma = turma;
	}

	public List<Estudante> getEstudante() {
		return estudante;
	}

	public void setEstudante(List<Estudante> estudante) {
		this.estudante = estudante;
	}

}


