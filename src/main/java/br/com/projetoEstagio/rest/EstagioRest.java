package br.com.projetoEstagio.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.JSONString;

import br.com.projetoEstagio.entity.Estagio;
import br.com.projetoEstagio.entity.Estudante;
import br.com.projetoEstagio.entity.Turma;
import br.com.projetoEstagio.jpa.EmpresaJPA;
import br.com.projetoEstagio.jpa.EstudanteJPA;
import br.com.projetoEstagio.jpa.InstituicaoJPA;
import br.com.projetoEstagio.jpa.TurmaJPA;
import br.com.projetoEstagio.pojo.EstagioPojo;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.EstagioService;

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
				estagio.setEstagioObrigatorio(jsonObject.optBoolean("obrigatorio"));
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
				
				estudante.setNome(jsonObject.optString("editEstudante"));
				estudante.setCpf(jsonObject.optString("editCpf"));
				estudante.setTurma(turmaJPA.findById(jsonObject.optLong("editTurma")));
				
				estudante = estudanteJPA.editarEstudante(estudante);
			
				estagio.setCadastroSGN(jsonObject.optBoolean("cadastroSGN"));
				estagio.setDataAditivo(formataData(jsonObject.optString("dataAditivo")));
				estagio.setDataFim(formataData(jsonObject.optString("dataFim")));
				estagio.setDataInicio(formataData(jsonObject.optString("dataInicio")));
				estagio.setDataRescisao(formataData(jsonObject.optString("dataRescisao")));
				estagio.setEmpresa(empresaJPA.findById(jsonObject.optJSONObject("empresa").optLong("id")));
				estagio.setEstagioObrigatorio(jsonObject.optBoolean("obrigatorio"));
				estagio.setEstudante(service.addEstudante(estudante));
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

	
	public static Date formataData(String data) throws Exception { 
		if (data == null || data.equals(""))
			return null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}

}
