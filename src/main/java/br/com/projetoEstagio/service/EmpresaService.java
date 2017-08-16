package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.EstagioJPA;
import br.com.projetoEstagio.restUtil.RestResponse;

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
	
	public Object deleteEmpresa(JSONArray emp, RestResponse response) throws Exception{
		EmpresaJPA empresa = new EmpresaJPA();
		EstagioJPA estagio = new EstagioJPA();
		
		List<Empresa> empresaEmUso = new ArrayList<Empresa>();
			
			if(emp != null && emp.length() > 0){
				for (int i = 0; i < emp.length(); i++) {
					List<Estagio> estagios = estagio.buscarPorEmpresa(emp.getLong(i));
					
					if(estagios.size() > 0){
						empresaEmUso.add(estagios.get(0).getEmpresa());	
					}else{
						empresa.deleteEmpresa(emp.getLong(i));
					}
				}
			}
			
			if(empresaEmUso.size() > 0){
				String nomeEmpresa = "";
				for (Empresa e: empresaEmUso) {
					if(!nomeEmpresa.contains(e.getNome())){		
						nomeEmpresa += e.getNome() + " - ";					
					}
				}
				response.setDescription(nomeEmpresa.replace(" * ", ", "));
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

