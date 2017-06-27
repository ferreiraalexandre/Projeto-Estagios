package br.com.projetoEstagio.service;

import java.util.List;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.EstagioJPA;
import br.com.projetoEstagio.jpa.EstudanteJPA;
import br.com.projetoEstagio.jpa.InstituicaoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.pojo.EstagioPojo;

public class EstagioService {

	public List<Estagio> listEstagio() throws Exception {
		EstagioJPA listEstagio = new EstagioJPA();
		return listEstagio.list();
	}
	
	public EstagioPojo listSelect() throws Exception {
		EstagioPojo estagioPojo = new EstagioPojo();
		EmpresaJPA empresa = new EmpresaJPA();
		EstudanteJPA estudante = new EstudanteJPA();
		InstituicaoJPA instituicao = new InstituicaoJPA();
		TurmaJPA turma = new TurmaJPA();
		
		estagioPojo.setEmpresa(empresa.list());
		estagioPojo.setEstudante(estudante.list());
		estagioPojo.setInstituicao(instituicao.list());
		estagioPojo.setTurma(turma.list());
		
	
		return estagioPojo;
	}


}
