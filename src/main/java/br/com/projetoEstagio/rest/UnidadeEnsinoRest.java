package br.com.projetoEstagio.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.UnidadeEnsino;
import br.com.projetoEstagio.restUtil.RestResponse;
import br.com.projetoEstagio.restUtil.UtilRest;
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
			String msg = "Unidade de ensino já cadastrada";

			Object result = service.addUnidadeEnsino(uni);
			if(result == null){
				return getResponseAdd(msg, result);
			}else{
				return getResponseAdd(result);				
			}
		}catch(Exception e){
			return getResponseError(e);
		}
	}
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public Response list() {

		try{
			UnidadeEnsinoService service = new UnidadeEnsinoService(); 

			List<UnidadeEnsino> e = service.listUnidade();

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

		try{
			UnidadeEnsinoService service = new UnidadeEnsinoService(); 
			this.response = new RestResponse();

			return getResponseRemove(service.deleteUnidade(id, this.response));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	
	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			UnidadeEnsino uni = getObjectMapper().readValue(json, UnidadeEnsino.class);			
			UnidadeEnsinoService service = new UnidadeEnsinoService(); 
			String msg = "Unidade de ensino já cadastrada";

			
			Object result = service.editarUnidade(uni);
			if(result == null){
				return getResponseEdit(msg, result);
			}else{
				return getResponseEdit(result);				
			}
		}catch(Exception e){
			return getResponseError(e);
		}
	}
}


