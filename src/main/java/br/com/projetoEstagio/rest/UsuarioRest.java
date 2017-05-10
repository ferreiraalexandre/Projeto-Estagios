package br.com.projetoEstagio.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.UnidadeEnsinoService;
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
	@DELETE
	@Path("/deletar/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response delete(@PathParam ("id") JSONArray id) {
		System.out.println("************ 1 ****************");
		try{
			UsuarioService service = new UsuarioService(); 

			service.deleteUsuario(id);

			return getResponseRemove();
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			UsuarioService service = new UsuarioService(); 

			List<Usuario> e = service.listUsuario();

			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	



}

