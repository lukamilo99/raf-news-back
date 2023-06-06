package rs.raf.rafnews.resource;

import rs.raf.rafnews.entity.Category;
import rs.raf.rafnews.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<Category> userList = categoryService.findAll();
        return Response.ok(userList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") int id) {
        Category category = categoryService.findById(id);
        return Response.ok(category).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) {
        Category createdCategory = categoryService.insert(category);
        return Response.status(Response.Status.CREATED)
                .entity(createdCategory)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") int id, Category updatedCategory) {
        updatedCategory.setId(id);
        Category category = categoryService.update(updatedCategory);
        return Response.ok(category).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") int id) {
        categoryService.deleteById(id);
        return Response.noContent().build();
    }
}
