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

import br.com.projetoEstagio.entity.Curso;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.CursoService;

@Path("/curso")
public class CursoRest extends UtilRest {
		
	public CursoRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){
		try{
			Curso cur = getObjectMapper().readValue(json, Curso.class);
			
			CursoService service = new CursoService(); 
					
			return getResponseAdd(service.addCurso(cur));
		}catch(Exception e){
			return getResponseError(e);
		}
	}
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public Response list() {

		try{
			CursoService service = new CursoService(); 

			List<Curso> e = service.listCurso();

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
			CursoService service = new CursoService(); 

			return getResponseRemove(service.deleteCurso(id, this.response));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	
	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			Curso cur = getObjectMapper().readValue(json, Curso.class);
			
			CursoService service = new CursoService(); 
					
			return getResponseEdit(service.editarCurso(cur));
		}catch(Exception e){
			return getResponseError(e);
		}
	}
}


