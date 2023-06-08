package rs.raf.rafnews.resource;

import rs.raf.rafnews.dto.comment.RequestCommentDto;
import rs.raf.rafnews.service.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(@Valid RequestCommentDto requestCommentDto) {
        commentService.insert(requestCommentDto);
        return Response.status(Response.Status.CREATED)
                .entity("CREATED")
                .build();
    }
}
