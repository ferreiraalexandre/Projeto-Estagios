package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Turma;

public interface TurmaInterface {
	
	public Turma addTurma(Turma tur);
	
	public List<Turma> list();
	
	public void deleteTurma(long id);
	
	public Turma editarTurma(Turma tur);
	
	public List<Turma> buscarSemId(Turma turma);

	public List<Turma> buscarPorId(Long id);
	
	public Turma findById(Long id);
}
