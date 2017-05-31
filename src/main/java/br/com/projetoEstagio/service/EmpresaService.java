package br.com.projetoEstagio.service;

import java.util.List;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.jpa.EmpresaJPA;

public class EmpresaService {

	public Object addEmpresa(Empresa emp) {
		EmpresaJPA empresa = new EmpresaJPA();
		empresa.addEmpresa(emp);
		
		return  empresa.list();

	}

	public List<Empresa> listEmpresa() throws Exception {
		EmpresaJPA listEmpresa = new EmpresaJPA();
		return listEmpresa.list();
	}
	
	/*public Object deleteUsuario(JSONArray usu) throws Exception{
		UsuarioJPA uni = new UsuarioJPA();
			
		if(usu != null && usu.length() > 0){
			for (int i = 0; i < usu.length(); i++) {
				uni.deleteUsuario(usu.getLong(i));
				System.out.println(usu.getLong(i));
			}
		}
		return uni.list();
	}*/

	/*public Object editarUsuario(Usuario usu) {
		UsuarioJPA user = new UsuarioJPA();
		user.editarUsuario(usu);
		return user.list();
	}*/
	
}

