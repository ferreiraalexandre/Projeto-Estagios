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

}