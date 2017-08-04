package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.jpa.InstituicaoJPA;
import br.com.projetoEstagio.restUtil.RestResponse;


public class InstituicaoService {

	public Object addInstituicao(Instituicao instituicao) {
		InstituicaoJPA inst = new InstituicaoJPA();
		inst.addInstituicao(instituicao);		
		return inst.list();
	}
	
	public List<Instituicao> validar(Instituicao inst) throws Exception {
		InstituicaoJPA jpa = new InstituicaoJPA();
		return jpa.validate(inst);
	}
	
	public List<Instituicao> listInstituicao() throws Exception {
		InstituicaoJPA inst = new InstituicaoJPA();
		return inst.list();
	}
	
	public Object deleteInstituicao(JSONArray instituicao, RestResponse response) throws Exception{
		InstituicaoJPA inst = new InstituicaoJPA();
		//EstagioJPA esta = new EstagioJPA();
		
		//List<Estagio> estagioEmUso = new ArrayList<Estagio>();
			
			if(instituicao != null && instituicao.length() > 0){
				for (int i = 0; i < instituicao.length(); i++) {
					/*List<Estagio> estagio = esta.buscarPorId(instituicao.getLong(i));
					
					if(estagio.size() > 0){
						estagioEmUso.add(estagio.get(0));	
					}else{*/
						inst.deleteInstituicao(instituicao.getLong(i));												
					//}
				}
			}
			
			/*if(estagioEmUso.size() > 0){
				String nomeEstagio = "";
				for (Estagio estagio : estagioEmUso) {
					if(!nomeEstagio.contains(estagio.getInstituicao().getNome())){		
						nomeEstagio += estagio.getInstituicao().getNome() + "  ";						
					}
				}
				response.setDescription(nomeEstagio.replace("  ", "; "));
			}			
	
			List<Estagio> listEstagios = inst.list();
			
			return listEstagios;*/
			return null;
	}
	
	public Object editarInstituicao(Instituicao instituicao) {
		InstituicaoJPA inst = new InstituicaoJPA();
		inst.editarInstituicao(instituicao);
		return inst.list();
	}
}
