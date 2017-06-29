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

import org.json.JSONObject;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.pojo.EstagioPojo;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.EstagioService;
import br.com.projetoEstagio.service.UsuarioService;

@Path("/estagio")
public class EstagioRest extends UtilRest {
	
	public EstagioRest(){
		
	}
	
	@GET
	@Path("/buscar")
	@Produces("application/json")
	public Response list(){
		
		try {
			EstagioService service = new EstagioService();
			
			List<Estagio> e = service.listEstagio();
			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

	@GET
	@Path("/buscarListSelect")
	@Produces("application/json")
	public Response listSelect(){
		
		try {
			EstagioService service = new EstagioService();
			
			EstagioPojo e = service.listSelect();
			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

	@POST
	@Path("/salva")
	@Consumes("application/json")
	@Produces("application/json")
	public Response salva( String json){

		try{
			JSONObject jsonObject = new JSONObject(json);
			EstagioService service = new EstagioService();
			
			if(!jsonObject.isNull("novoEstudante")){
				TurmaJPA turma = new TurmaJPA();
				Estudante estudante = new Estudante();

				estudante.setNome(jsonObject.optString("novoEstudante"));
				estudante.setCpf(jsonObject.optString("cpf"));
				JSONObject turmaJSON =	jsonObject.optJSONObject("turma");
				Long id = turmaJSON.optLong("id");
			//	estudante.setTurma(turma.buscarPorId(id));
				
				
				service.addEstudante(estudante);
			}
			
			Estagio estagio = getObjectMapper().readValue(json, Estagio.class);
										
			return getResponseAdd(service.addEstagio(estagio));
		}catch(Exception e){
			return getResponseError(e);
		}
	}

}
