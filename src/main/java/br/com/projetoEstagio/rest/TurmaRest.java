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

import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.TurmaService;

@Path("/turma")
public class TurmaRest extends UtilRest {
		
	public TurmaRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){

		try{
			TurmaService service = new TurmaService(); 
			Turma turma = getObjectMapper().readValue(json, Turma.class);										
			String msg = "Turma já cadastrada";
			
			Object result = service.addTurma(turma);
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
			TurmaService service = new TurmaService(); 

			List<Turma> e = service.listTurma();

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
			TurmaService service = new TurmaService(); 

			return getResponseRemove(service.deleteTurma(id));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			Turma tur = getObjectMapper().readValue(json, Turma.class);			
			TurmaService service = new TurmaService();
			String msg = "Turma já cadastrada";

			
			Object result = service.editarTurma(tur);
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

