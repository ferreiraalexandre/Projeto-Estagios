package br.com.projetoEstagio.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="estagio")
public class Estagio {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "dataInicio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;

	@Column(name = "dataFim", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;

	@Column(name = "dataAditivo", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAditivo;

	@Column(name = "dataRescisao", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRescisao;

	@Column(name = "situacao", nullable = false)
	private String situacao;

	@Column(name = "cadastroSGN", nullable = false)
	private Boolean cadastroSGN;
	
	@Column(name = "estagioObrigatorio", nullable = true)
	private Boolean estagioObrigatorio;

	@Column(name = "observacao")
	private String observacao;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "empresaId", nullable = false)
	private Empresa empresa;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "instituicaoId", nullable = false)
	private Instituicao instituicao;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "turmaId", nullable = false)
	private Turma turma;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "estudanteId", nullable = false)
	private Estudante estudante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataAditivo() {
		return dataAditivo;
	}

	public void setDataAditivo(Date dataAditivo) {
		this.dataAditivo = dataAditivo;
	}

	public Date getDataRescisao() {
		return dataRescisao;
	}

	public void setDataRescisao(Date dataRescisao) {
		this.dataRescisao = dataRescisao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Boolean getCadastroSGN() {
		return cadastroSGN;
	}

	public void setCadastroSGN(Boolean cadastroSGN) {
		this.cadastroSGN = cadastroSGN;
	}

	public Boolean getEstagioObrigatorio() {
		return estagioObrigatorio;
	}

	public void setEstagioObrigatorio(Boolean estagioObrigatorio) {
		this.estagioObrigatorio = estagioObrigatorio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

}
