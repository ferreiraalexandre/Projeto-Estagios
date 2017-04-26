package br.com.projetoEstagio.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;


import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.service.UnidadeEnsinoService;

@Path("/unidadeEnsino")
public class UnidadeEnsinoRest extends UtilRest {
		
	public UnidadeEnsinoRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){
		try{
			UnidadeEnsino uni = getObjectMapper().readValue(json, UnidadeEnsino.class);
			
			UnidadeEnsinoService service = new UnidadeEnsinoService(); 
					
			return getResponseAdd(service.addUnidadeEnsino(uni));
		}catch(Exception e){
			return getResponseError(e);
		}
	}
}


