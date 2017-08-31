package br.com.projetoEstagio.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

import org.apache.log4j.Logger;
import org.json.JSONArray;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.restUtil.RestResponse;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.UsuarioService;

@Path("/usuario")
public class UsuarioRest extends UtilRest {
	
	public UsuarioRest() {
	}
	
	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json) throws Exception{

		try{
			UsuarioService service = new UsuarioService(); 
			Usuario usuario = getObjectMapper().readValue(json, Usuario.class);
			String msg = "Email j� cadastrado";
			
			Object result = service.addUsuario(usuario);
			if(result == null){
				return getResponseAdd(msg, result);
			}else{
				return getResponseAdd(result);				
			}
			
		}catch(Exception e){
			return getResponseAdd("Erro ao cadastrar novo usuario", e);
		}
	}
	
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public Response list() {

		try{
			UsuarioService service = new UsuarioService(); 
			
			this.callMeInAppError("TESTE ERROR");
			
			String teste = System.getProperty("catalina.home") + "//logs";
			File file = new File(teste);
			File afile[] = file.listFiles();
			int i = 0;
			for (int j = afile.length; i < j; i++) {
				File arquivos = afile[i];
				System.out.println(arquivos.getName());
				
				FileReader ler = new FileReader(arquivos);
	            BufferedReader reader = new BufferedReader(ler);  
	            String linha;
	            while( (linha = reader.readLine()) != null ){
	                System.out.println(linha);
	            }
			}

			List<Usuario> e = service.listUsuario();

			return getResponseList(e);
		} catch (Exception e) {
			return  getResponseError(e);
		}
	}
	
	@GET
	@Path("/getCoordenadores")
	@Produces("application/json")
	public Response listSelect(){
		
		try {
			UsuarioService service = new UsuarioService();
			
			List<Usuario> e = service.listCoordenadores();
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
			UsuarioService service = new UsuarioService(); 
			this.response = new RestResponse();
			return getResponseRemove(service.deleteUsuario(id, this.response));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			Usuario usu = getObjectMapper().readValue(json, Usuario.class);			
			UsuarioService service = new UsuarioService(); 
			String msg = "Email j� cadastrado";
			
			Object result = service.editarUsuario(usu);
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

