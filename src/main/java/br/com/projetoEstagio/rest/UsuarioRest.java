package br.com.projetoEstagio.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.com.projeto.exception.PrincipalException;
import br.com.projetoEstagio.objetos.UsuarioObjeto;

@Path("usuario")
public class UsuarioRest extends UtilRest {
		
	public UsuarioRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/*")
	public Response addUsuarioMaster(UsuarioObjeto usuario, @Context HttpServletRequest request) throws PrincipalException {
		return null;
		
	}
}