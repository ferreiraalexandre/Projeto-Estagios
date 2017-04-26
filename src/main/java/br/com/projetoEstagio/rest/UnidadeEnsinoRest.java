package br.com.projetoEstagio.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/unidadeEnsino")
public class UnidadeEnsinoRest extends UtilRest {
		
	public UnidadeEnsinoRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String idCategory){
		System.out.println("teste");
		return null;
		
	}
}


