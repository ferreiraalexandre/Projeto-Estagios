package br.com.projetoEstagio.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.UsuarioService;

@Path("/usuario")
public class UsuarioRest extends UtilRest {
		
	public UsuarioRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){

		try{
			Usuario usu = getObjectMapper().readValue(json, Usuario.class);
			
			UsuarioService service = new UsuarioService(); 
					
			return getResponseAdd(service.addUsuario(usu));
		}catch(Exception e){
			return getResponseError(e);
		}
	}
	
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public Response list() {

		try{
			UsuarioService service = new UsuarioService(); 

			List<Usuario> e = service.listUsuario();

			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

}

