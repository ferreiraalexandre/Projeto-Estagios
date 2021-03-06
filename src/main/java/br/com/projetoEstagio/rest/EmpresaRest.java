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

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.restUtil.RestResponse;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.EmpresaService;

@Path("/empresa")
public class EmpresaRest extends UtilRest {
		
	public EmpresaRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){

		try{
			EmpresaService service = new EmpresaService(); 
			Empresa empresa = getObjectMapper().readValue(json, Empresa.class);
			String msg = "Empresa já cadastrada";
					
			Object result = service.addEmpresa(empresa);
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
			EmpresaService service = new EmpresaService(); 
			List<Empresa> e = service.listEmpresa();
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
			EmpresaService service = new EmpresaService(); 
			this.response = new RestResponse();
			return getResponseRemove(service.deleteEmpresa(id, this.response));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	
	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{			
			EmpresaService service = new EmpresaService(); 			
			Empresa emp = getObjectMapper().readValue(json, Empresa.class);
			String msg = "Empresa já cadastrada";
			
			Object result = service.editarEmpresa(emp);
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

