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

import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.restUtil.RestResponse;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.InstituicaoService;

@Path("/instituicao")
public class InstituicaoRest extends UtilRest {
		
	public InstituicaoRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){
		try{
			Instituicao instituicao = getObjectMapper().readValue(json, Instituicao.class);
			InstituicaoService service = new InstituicaoService();
			String msg = "Instituição já cadastrada";
					
			Object result = service.addInstituicao(instituicao);
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
			InstituicaoService service = new InstituicaoService(); 

			List<Instituicao> e = service.listInstituicao();

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
			InstituicaoService service = new InstituicaoService(); 
			this.response = new RestResponse();
			return getResponseRemove(service.deleteInstituicao(id, this.response));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	
	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			Instituicao instituicao = getObjectMapper().readValue(json, Instituicao.class);			
			InstituicaoService service = new InstituicaoService(); 
			String msg = "Instituição já cadastrada";
					
			Object result = service.editarInstituicao(instituicao);
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


