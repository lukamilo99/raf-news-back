package rs.raf.rafnews.resource;

import rs.raf.rafnews.dto.LoginDto;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/user")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        User createdUser = userService.insert(user);
        return Response.status(Response.Status.CREATED)
                .entity(createdUser)
                .build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginDto loginDto) {
        Map<String, String> response = new HashMap<>();
        String jwt = userService.login(loginDto.getUsername(), loginDto.getPassword());
        if (jwt == null) {
            response.put("message", "Bad credentials");
            return Response.status(422, "Unauthorized").entity(response).build();
        }
        response.put("jwt", jwt);
        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> userList = userService.findAll();
        return Response.ok(userList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        User user = userService.findById(id);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User updatedUser) {
        updatedUser.setId(id);
        User user = userService.update(updatedUser);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        userService.deleteById(id);
        return Response.noContent().build();
    }
}
