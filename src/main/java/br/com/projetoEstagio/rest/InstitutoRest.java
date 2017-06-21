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

import br.com.projetoEstagio.entity.Instituto;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.InstitutoService;

@Path("/instituto")
public class InstitutoRest extends UtilRest {
		
	public InstitutoRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){
		try{
			Instituto instituto = getObjectMapper().readValue(json, Instituto.class);
			
			InstitutoService service = new InstitutoService(); 
					
			return getResponseAdd(service.addInstituto(instituto));
		}catch(Exception e){
			return getResponseError(e);
		}
	}
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public Response list() {

		try{
			InstitutoService service = new InstitutoService(); 

			List<Instituto> e = service.listInstituto();

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
			InstitutoService service = new InstitutoService(); 

			return getResponseRemove(service.deleteInstituto(id, this.response));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	
	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			Instituto instituto = getObjectMapper().readValue(json, Instituto.class);
			
			InstitutoService service = new InstitutoService(); 
					
			return getResponseEdit(service.editarInstituto(instituto));
		}catch(Exception e){
			return getResponseError(e);
		}
	}
}


