package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.jpa.EmpresaJPA;

public class EmpresaService {

	public Object addEmpresa(Empresa emp) {
		EmpresaJPA empresa = new EmpresaJPA();
		Object obj = null;
		
		List<Empresa> retorno = empresa.buscarPorNome(emp);
		if(retorno.size() > 0){
			return obj;
		}else{
			empresa.addEmpresa(emp);
			return  empresa.list();
			
		}
	}

	public List<Empresa> listEmpresa() throws Exception {
		EmpresaJPA listEmpresa = new EmpresaJPA();
		return listEmpresa.list();
	}
	
	public Object deleteEmpresa(JSONArray emp) throws Exception{
		EmpresaJPA empresa = new EmpresaJPA();
			
		if(emp != null && emp.length() > 0){
			for (int i = 0; i < emp.length(); i++) {
				empresa.deleteEmpresa(emp.getLong(i));
				System.out.println(emp.getLong(i));
			}
		}
		return empresa.list();
	}
	
	public Object editarEmpresa(Empresa emp) {
		EmpresaJPA empresa = new EmpresaJPA();
		Object obj = null;
		
		List<Empresa> retorno = empresa.buscarPorNome(emp);
		if(retorno.size() > 0){
			for (Empresa u : retorno) {
				if(u.getId() != emp.getId()){
					return obj;
				}					
			}				
		}
		empresa.editarEmpresa(emp);
		return empresa.list();
	}
	
}

