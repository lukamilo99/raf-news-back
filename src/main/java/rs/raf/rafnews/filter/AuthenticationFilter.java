package rs.raf.rafnews.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.repository.UserRepository;
import rs.raf.rafnews.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    private UserRepository userRepository;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        if (!this.isAuthRequired(containerRequestContext)) {
            return;
        }
        String token = getToken(containerRequestContext);
        String path = containerRequestContext.getUriInfo().getPath();
        if (!isAuthorized(token, path)) {
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

    private boolean isAuthorized(String token, String path) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = JWT.decode(token);

        if (isTokenExpired(jwt)) {
            return false;
        }
        else {
            jwt = verifier.verify(token);
        }

        String username = jwt.getSubject();
        String role = jwt.getClaim("role").asString();

        if(path.contains("public")) {
            User user = this.userRepository.findByUsername(username);
            return user != null;
        }
        else if(path.contains("user")) {
            if(role.equals("ADMIN")) {
                User user = this.userRepository.findByUsername(username);
                return user != null;
            }
            else {
                return false;
            }
        }
        else return true;
    }

    private boolean isTokenExpired(DecodedJWT jwt) {
        Date expiresAt = jwt.getExpiresAt();
        return expiresAt.before(new Date());
    }
}
