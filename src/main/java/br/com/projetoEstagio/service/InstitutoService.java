package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;
import br.com.projetoEstagio.restUtil.RestResponse;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.jpa.InstitutoJPA;


public class InstitutoService {

	public Object addInstituto(Instituicao instituto) {
		InstitutoJPA inst = new InstitutoJPA();
		inst.addInstituto(instituto);
		
		return inst.list();
		
	}
	
	public List<Instituicao> listInstituto() throws Exception {
		InstitutoJPA inst = new InstitutoJPA();
		return inst.list();
	}
	
	public Object deleteInstituto(JSONArray instituto, RestResponse response) throws Exception{
		InstitutoJPA inst = new InstitutoJPA();
		//EstagioJPA esta = new EstagioJPA();
		
		//List<Estagio> estagioEmUso = new ArrayList<Estagio>();
			
			if(instituto != null && instituto.length() > 0){
				for (int i = 0; i < instituto.length(); i++) {
					/*List<Estagio> estagio = esta.buscarPorId(instituto.getLong(i));
					
					if(estagio.size() > 0){
						estagioEmUso.add(estagio.get(0));	
					}else{*/
						inst.deleteInstituto(instituto.getLong(i));												
					//}
				}
			}
			
			/*if(estagioEmUso.size() > 0){
				String nomeEstagio = "";
				for (Estagio estagio : estagioEmUso) {
					if(!nomeEstagio.contains(estagio.getInstituto().getNome())){		
						nomeEstagio += estagio.getInstituto().getNome() + "  ";						
					}
				}
				response.setDescription(nomeEstagio.replace("  ", "; "));
			}			
	
			List<Estagio> listEstagios = inst.list();
			
			return listEstagios;*/
			return null;
	}
	
	public Object editarInstituto(Instituicao instituto) {
		InstitutoJPA inst = new InstitutoJPA();
		inst.editarInstituto(instituto);
		return inst.list();
	}
}
