package br.com.projetoEstagio.interfaces;

import java.util.Date;
import java.util.List;

import br.com.projetoEstagio.entity.Estagio;

public interface EstagioInterface {

	public List<Estagio> list();
	
	public Estagio addEstagio(Estagio estagio);
	
	public Estagio editarEstagio(Estagio estagio);

	public void deleteEstagio(long id);
	
	public List<Estagio> filtrar(Date dataInicio, Date dataFim, Long cursoId, Long empresaId);
	
	public List<Object> buscarEstagioComRescisao();
	
	public List<Object> buscarTurmaComEstagiario();
}
