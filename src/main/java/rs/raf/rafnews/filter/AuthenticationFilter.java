package rs.raf.rafnews.filter;

import rs.raf.rafnews.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        if (!this.isAuthRequired(containerRequestContext)) {
            return;
        }
        String token = getToken(containerRequestContext);
        String path = containerRequestContext.getUriInfo().getPath();
        if (!this.userService.isAuthorized(token, path)) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext request) {
        if (request.getUriInfo().getPath().contains("login")
                || request.getUriInfo().getPath().contains("public")
                || request.getRequest().getMethod().equals("OPTIONS")) {
            return false;
        }
        return true;
    }

    private String getToken(ContainerRequestContext request) {
        String token = request.getHeaderString("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }
        else return null;
    }
}
