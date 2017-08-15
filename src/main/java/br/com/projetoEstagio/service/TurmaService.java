package br.com.projetoEstagio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.jpa.UsuarioJPA;
import br.com.projetoEstagio.restUtil.RestResponse;

public class TurmaService {

	public Object addTurma(Turma tur) {
		TurmaJPA turma = new TurmaJPA();									
		Object obj = null;
				
		List<Turma> retorno = turma.buscarSemId(tur);
		if(retorno.size() > 0){
			return obj;
		}else{
			obj = turma.addTurma(tur);			
			return turma.list();							
		}
	}

	public List<Turma> listTurma() throws Exception {
		TurmaJPA listTurma = new TurmaJPA();
		return listTurma.list();
	}
	
	public Object deleteTurma(JSONArray tur) throws Exception{
		TurmaJPA turma = new TurmaJPA();
			
		if(tur != null && tur.length() > 0){
			for (int i = 0; i < tur.length(); i++) {
				turma.deleteTurma(tur.getLong(i));
				System.out.println(tur.getLong(i));
			}
		}
		return turma.list();
	}
	///////////PAREI AQUI, COMECAR ESSE METODO DO ZERO	
	/*public Object deleteTurma(JSONArray users, RestResponse response) throws Exception{
		UsuarioJPA use = new UsuarioJPA();
		EmpresaJPA emp = new EmpresaJPA();
		TurmaJPA tur = new TurmaJPA();
		
		List<Usuario> usuarioEmUso = new ArrayList<Usuario>();
			
			if(users != null && users.length() > 0){
				for (int i = 0; i < users.length(); i++) {
					List<Empresa> empresa = emp.buscarPorUsuario(users.getLong(i));
					List<Turma> turma = tur.buscarPorUsuario(users.getLong(i));
					
					if(empresa.size() > 0 || turma.size() > 0){
						if(empresa.size() > 0){
							usuarioEmUso.add(empresa.get(0).getUsuario());
						}
						if(turma.size() > 0){
							usuarioEmUso.add(turma.get(0).getUsuario());
						}
					}else{
						use.deleteUsuario(users.getLong(i));												
					}
				}
			}
			
			if(usuarioEmUso.size() > 0){
				String nomeUsuario = "";
				for (Usuario usuario : usuarioEmUso) {
					if(!nomeUsuario.contains(usuario.getNome())){		
						nomeUsuario += usuario.getNome() + " - ";						
					}
				}
				response.setDescription(nomeUsuario.replace(" * ", ", "));
			}			
	
			List<Usuario> usuarios = use.list();
			
			return usuarios;
	}*/

	public Object editarTurma(Turma tur) {
		TurmaJPA turma = new TurmaJPA();
		Object obj = null;
		
		List<Turma> retorno = turma.buscarSemId(tur);
		if(retorno.size() > 0){
			for (Turma u : retorno) {
				if(u.getId() != tur.getId()){
					return obj;
				}					
			}				
		}
		turma.editarTurma(tur);
		return turma.list();
		
	}
	
}
