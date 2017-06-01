package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Empresa;

public interface EmpresaInterface {
	
	public Empresa addEmpresa(Empresa emp);
	
	public List<Empresa> list();
	
	public void deleteEmpresa(long id);
	
	public Empresa editarEmpresa(Empresa emp);

	//public Empresa buscarPorId(Long id); 
}
