package br.com.projetoEstagio.jpa;

import java.util.List;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.interfaces.UsuarioInterface;


public class UsuarioJPA  extends JPAAbstract<Usuario, Long> implements UsuarioInterface {

	public Usuario addUsuario(Usuario usu) {
		return this.add(usu);
	}

	public List<Usuario> list(){
		return this.list("");
		
	}
	public void deleteUsuario(long id){
		this.remove(id);
	}

	public Usuario editarUsuario(Usuario usu) {
		return this.edit(usu);
	}
	
	public List<Usuario> buscarPorId(Long id) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.unidadeEnsino.id = '"+ id +"'");
	}
	
	
	public List<Usuario> listCoordenadores() {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.tipo = 'Coordenador'");
	}
	
	public List<Usuario> validate(Usuario user) {
		return this.findAllByIds("SELECT U FROM "+ this.getEntityName() +" U WHERE U.email = '"+ user.getEmail() +"'");
	}
	
	public Usuario buscar(String email) {
		return this.getObject("SELECT U FROM "+ this.getEntityName() +" U WHERE U.email = '"+ email +"'");
	}	
	
	public Usuario getUserAuth(String email, String senha) {
		return this.getObject("SELECT u FROM "+this.getEntityName()+" u WHERE u.email='"+email+"' AND u.senha='"+senha+"'");
	}	


	

}