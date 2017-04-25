package br.com.projetoEstagio.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/usuario")
public class UsuarioRest extends UtilRest {
		
	public UsuarioRest() {
	}
	
	@POST
	@Path("/salva/{id}")
	@Produces("application/json")
	public Response getListFilterCategory(@PathParam("id") String idCategory){
		return null;
		
	}
}


