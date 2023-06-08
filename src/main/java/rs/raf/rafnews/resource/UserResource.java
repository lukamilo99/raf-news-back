package rs.raf.rafnews.resource;

import rs.raf.rafnews.dto.user.LoginDto;
import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
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
    public Response createUser(RequestUserDto user) {
        userService.insert(user);
        return Response.status(Response.Status.CREATED)
                .entity("CREATED")
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
        List<ResponseUserDto> userList = userService.findAll();
        return Response.ok(userList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        ResponseUserDto user = userService.findById(id);
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, RequestUserDto updatedUser) {
        updatedUser.setId(id);
        userService.update(updatedUser);
        return Response.ok("UPDATED").build();
    }

    @PUT
    @Path("/activate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateUser(@PathParam("id") int id) {
        userService.activateUserById(id);
        return Response.ok("UPDATED").build();
    }

    @PUT
    @Path("/deactivate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deactivateUser(@PathParam("id") int id) {
        userService.deactivateUserById(id);
        return Response.ok("UPDATED").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        userService.deleteById(id);
        return Response.noContent().build();
    }
}
