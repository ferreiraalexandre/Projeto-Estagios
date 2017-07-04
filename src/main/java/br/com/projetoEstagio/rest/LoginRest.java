package br.com.projetoEstagio.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.LoginService;


@Path("/login")
public class LoginRest extends UtilRest  {

	@POST
	@Path("/auth")
	@Consumes("application/*")
	public Response auth(String json)  {
		try {
			Usuario e = getObjectMapper().readValue(json, Usuario.class);

			LoginService service = this.service.newInstance();
			return getResponseList(service.auth(e));
		} catch (Exception e) {
			return getResponseError(e);
		}
		
	}
}


