package br.com.projetoEstagio.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import br.com.projetoEstagio.entity.Usuario;
import br.com.projetoEstagio.restUtil.UtilRest;
import br.com.projetoEstagio.service.LoginService;


@Path("/login")
public class LoginRest extends UtilRest  {

	@POST
	@Path("/auth")
	@Consumes("application/json")
	@Produces("application/json")
	public Response auth(String json)  {
		try {
			JSONObject user = new JSONObject(json);
			
			LoginService login = new LoginService();
						
			return getResponseList(login.buscarUsuario(user.optString("email"), user.optString("password")));

		} catch (Exception e) {
			return getResponseError(e);
		}
		
	}
}


