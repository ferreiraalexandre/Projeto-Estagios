package br.com.projetoEstagio.filter;
import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import br.com.projetoEstagio.auth.Auth;
import br.com.projetoEstagio.restUtil.UtilRest;

@PreMatching
@Priority(Priorities.AUTHORIZATION)
@Provider
public class interceptor extends UtilRest implements ContainerRequestFilter{
 
	@Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
		String url = requestContext.getUriInfo().getPath();

        if (!url.endsWith("/")) {
            url += "/";
        }
        
        if (requestContext.getMethod().equals("OPTIONS")) {
            return;
        }
        if (!url.equals("/login/auth/")) {
            String authorizationHeader = requestContext.getHeaderString("authorization");
            if(authorizationHeader == null) {
            	authorizationHeader = "";
            }
            
            if (!url.equals("login/auth/")) {
                Auth auth = new Auth();
                try {
                	auth.validate(authorizationHeader.replace("Bearer ", ""));
                	int permission = Integer.parseInt(auth.permission(authorizationHeader.replace("Bearer ", "")));
//                	
//                	PermissionControl permissionControl = new PermissionControl();
//                	
//                	if(UserPermission.REQUEST.getValue() == permission){
//                		permissionControl.request(url);
//                	}else if(UserPermission.STOCK.getValue() == permission){
//                		permissionControl.stock(url);
//                	}
                	
				} catch (Exception e) {
                	requestContext.abortWith(this.getResponseError(e));
				}

            } else {
                if (authorizationHeader != null) {}
            }
        }
       
    }
}