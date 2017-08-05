package br.com.projetoEstagio.interfaces;

import java.util.List;

import br.com.projetoEstagio.entity.Usuario;

public interface UsuarioInterface {
	
	public Usuario addUsuario(Usuario usu);
	
	public List<Usuario> list();
	
	public void deleteUsuario(long id);
	
	public Usuario editarUsuario(Usuario usu);

	public List<Usuario> buscarPorId(Long id);
	
	public List<Usuario> listCoordenadores();
	
	public List<Usuario> validate(Usuario user);
	
	public Usuario buscar(String email);
	
	public Usuario getUserAuth(String email, String senha);
}
