package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.jpa.EstagioJPA;
import br.com.projetoEstagio.jpa.InstituicaoJPA;
import br.com.projetoEstagio.restUtil.RestResponse;


public class InstituicaoService {

	public Object addInstituicao(Instituicao instituicao) {
		InstituicaoJPA inst = new InstituicaoJPA();
		Object obj = null;
				
		List<Instituicao> retorno = inst.buscarPorNome(instituicao);
		if(retorno.size() > 0){
			return obj;
		}else{
			inst.addInstituicao(instituicao);
			return inst.list();
			
		}
	}
	
	public List<Instituicao> listInstituicao() throws Exception {
		InstituicaoJPA inst = new InstituicaoJPA();
		return inst.list();
	}
	
	public Object deleteInstituicao(JSONArray instituicao, RestResponse response) throws Exception{
		InstituicaoJPA inst = new InstituicaoJPA();
		EstagioJPA esta = new EstagioJPA();
		
		List<Instituicao> instituicaoEmUso = new ArrayList<Instituicao>();
			
			if(instituicao != null && instituicao.length() > 0){
				for (int i = 0; i < instituicao.length(); i++) {
					List<Estagio> estagio = esta.buscarPorInstituicao(instituicao.getLong(i));
					
					if(estagio.size() > 0){
						instituicaoEmUso.add(estagio.get(0).getInstituicao());	
					}else{
						inst.deleteInstituicao(instituicao.getLong(i));
					}
				}
			}
			
			if(instituicaoEmUso.size() > 0){
				String nomeInstituicao = "";
				for (Instituicao insti : instituicaoEmUso) {
					if(!nomeInstituicao.contains(insti.getNome())){		
						nomeInstituicao += insti.getNome() + " - ";					
					}
				}
				response.setDescription(nomeInstituicao.replace(" * ", ", "));
			}

			return inst.list();
	}
	
	public Object editarInstituicao(Instituicao instituicao) {
		InstituicaoJPA inst = new InstituicaoJPA();
		Object obj = null;
		
		List<Instituicao> retorno = inst.buscarPorNome(instituicao);
		if(retorno.size() > 0){
			for (Instituicao u : retorno) {
				if(u.getId() != instituicao.getId()){
					return obj;
				}					
			}				
		}
		inst.editarInstituicao(instituicao);
		return inst.list();
	}
}













