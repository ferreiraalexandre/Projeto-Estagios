package br.com.projetoEstagio.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import br.com.projetoEstagio.entity.Empresa;
import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.entity.Instituicao;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.EstudanteJPA;
import br.com.projetoEstagio.jpa.InstituicaoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.pojo.EstagioPojo;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.EmpresaService;
import br.com.projetoEstagio.service.EstagioService;
import br.com.projetoEstagio.service.EstudanteService;

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
				TurmaJPA turmaJPA = new TurmaJPA();
				EmpresaJPA empresaJPA = new EmpresaJPA();
				InstituicaoJPA instituicaoJPA = new InstituicaoJPA();
				Estudante estudante = new Estudante();
				Estagio estagio = new Estagio();
				
				estudante.setNome(jsonObject.optString("novoEstudante"));
				estudante.setCpf(jsonObject.optString("cpf"));
				estudante.setTurma(turmaJPA.findById(jsonObject.optJSONObject("turma").optLong("id")));
			
				estagio.setCadastroSGN(jsonObject.optBoolean("cadastroSGN"));
				estagio.setDataAditivo(formataData(jsonObject.optString("dataAditivo")));
				estagio.setDataFim(formataData(jsonObject.optString("dataFim")));
				estagio.setDataInicio(formataData(jsonObject.optString("dataInicio")));
				estagio.setDataRescisao(formataData(jsonObject.optString("dataRescisao")));
				estagio.setEmpresa(empresaJPA.findById(jsonObject.optJSONObject("empresa").optLong("id")));
				estagio.setEstagioObrigatorio(jsonObject.optBoolean("estagioObrigatorio"));
				estagio.setEstudante(service.addEstudante(estudante));
				estagio.setInstituicao(instituicaoJPA.findById(jsonObject.optJSONObject("instituicao").optLong("id")));
				estagio.setObservacao(jsonObject.optString("observacao"));
				estagio.setSituacao(jsonObject.optString("situacao"));
				estagio.setTurma(estudante.getTurma());
				return getResponseAdd(service.addEstagio(estagio));
			}
			if(!jsonObject.isNull("editEstudante")){
				TurmaJPA turmaJPA = new TurmaJPA();
				EmpresaJPA empresaJPA = new EmpresaJPA();
				InstituicaoJPA instituicaoJPA = new InstituicaoJPA();
				EstudanteJPA estudanteJPA= new EstudanteJPA();
				Estudante estudante = new Estudante();
				Estagio estagio = new Estagio();
				
				estudante.setId(jsonObject.optJSONObject("editEstudante").optLong("id"));
				estudante.setNome(jsonObject.optJSONObject("editEstudante").optString("nome"));
				estudante.setCpf(jsonObject.optString("editCpf"));
				estudante.setTurma(turmaJPA.findById(jsonObject.optLong("editTurma")));
				
				estudante = estudanteJPA.editarEstudante(estudante);
			
				estagio.setCadastroSGN(jsonObject.optBoolean("cadastroSGN"));
				estagio.setDataAditivo(formataData(jsonObject.optString("dataAditivo")));
				estagio.setDataFim(formataData(jsonObject.optString("dataFim")));
				estagio.setDataInicio(formataData(jsonObject.optString("dataInicio")));
				estagio.setDataRescisao(formataData(jsonObject.optString("dataRescisao")));
				estagio.setEmpresa(empresaJPA.findById(jsonObject.optJSONObject("empresa").optLong("id")));
				estagio.setEstagioObrigatorio(jsonObject.optBoolean("estagioObrigatorio"));
				estagio.setEstudante(estudante);
				estagio.setInstituicao(instituicaoJPA.findById(jsonObject.optJSONObject("instituicao").optLong("id")));
				estagio.setObservacao(jsonObject.optString("observacao"));
				estagio.setSituacao(jsonObject.optString("situacao"));
				estagio.setTurma(estudante.getTurma());
				return getResponseAdd(service.addEstagio(estagio));
	
			}else{
				
				Estagio estagio = getObjectMapper().readValue(json, Estagio.class);
				
				return getResponseAdd(service.addEstagio(estagio));
			}
			
		}catch(Exception e){
			return getResponseError(e);
		}
	}
	
	@PUT
	@Path("/editar")
	@Produces("application/json")
	public Response editar(String json) {

		try{
			JSONObject jsonObject = new JSONObject(json);
			EstagioService estagioService = new EstagioService(); 
			EstudanteService estudanteService = new EstudanteService(); 
			
			if(!jsonObject.isNull("editEstudante")){
				Estudante estudante = new Estudante();
				Estagio estagio = new Estagio();
				
				estudante = getObjectMapper().readValue(jsonObject.optJSONObject("editEstudante").toString(), Estudante.class);
				
				
				estagio.setId(jsonObject.optLong("id"));
				estagio.setCadastroSGN(jsonObject.optBoolean("cadastroSGN"));
				estagio.setDataAditivo(formataData(jsonObject.optString("dataAditivo")));
				estagio.setDataFim(formataData(jsonObject.optString("dataFim")));
				estagio.setDataInicio(formataData(jsonObject.optString("dataInicio")));
				estagio.setDataRescisao(formataData(jsonObject.optString("dataRescisao")));
				estagio.setEmpresa(getObjectMapper().readValue(jsonObject.optJSONObject("empresa").toString(), Empresa.class));
				estagio.setEstagioObrigatorio(jsonObject.optBoolean("estagioObrigatorio"));
				estagio.setEstudante(estudanteService.editarEstudante(estudante));
				estagio.setInstituicao(getObjectMapper().readValue(jsonObject.optJSONObject("instituicao").toString(), Instituicao.class));
				estagio.setObservacao(jsonObject.optString("observacao"));
				estagio.setSituacao(jsonObject.optString("situacao"));
				estagio.setTurma(estudante.getTurma());

				return getResponseEdit(estagioService.editarEstagio(estagio));
			}
			return null;
		}catch(Exception e){
			return getResponseError(e);
		}
	}
	
	@DELETE
	@Path("/deletar/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response delete(@PathParam ("id") JSONArray id) {

		try{
			EstagioService service = new EstagioService();  

			return getResponseRemove(service.deleteEstagio(id));
		} catch (Exception e) {
			return getResponseError(e);
		}
	}

	
	public static Date formataData(String data) throws Exception { 
		if (data == null || data.equals(""))
			return null;
        Date date = null;
        try {
        	DateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            date = formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}
	
	@POST
	@Path("/filtro")
	@Consumes("application/json")
	@Produces("application/json")
	public Response filtro(String json){
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			
			Date dataInicio = formataData(jsonObject.optString("dataInicio"));
			Date dataFim = formataData(jsonObject.optString("dataFim"));
			Long cursoId = !jsonObject.isNull("curso") ? jsonObject.optJSONObject("curso").optLong("id") : null;
			Long empresaId = !jsonObject.isNull("empresa") ? jsonObject.optJSONObject("empresa").optLong("id") : null;
			EstagioService service = new EstagioService();
			
			List<Estagio> e = service.filtrarEstagio(dataInicio, dataFim, cursoId, empresaId);
			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}
	
	@GET
	@Path("/vencendo")
	@Produces("application/json")
	public Response vencendo(){
		
		try {
			EstagioService service = new EstagioService();
			
			List<Estagio> e = service.estagioVencendo();
			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}


	@GET
	@Path("/relatorio")
	@Produces("application/json")
	public Response relatorio(){
		
		try {
			EstagioService service = new EstagioService();
			
			String e = service.relatorio();
			return getResponseList(e);
		} catch (Exception e) {
			return getResponseError(e);
		}
	}


}
