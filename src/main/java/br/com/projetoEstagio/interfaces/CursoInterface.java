package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Curso;

public interface CursoInterface {
	
	public Curso addCurso(Curso cur);
	
	public List<Curso> list();
	
	public void deleteCurso(long id);
	
	public Curso editarCurso(Curso cur);

}
