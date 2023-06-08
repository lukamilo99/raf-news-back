package rs.raf.rafnews.resource;

import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDtoCMS;
import rs.raf.rafnews.dto.news.ResponseNewsDtoNP;
import rs.raf.rafnews.dto.news.ResponseNewsFullDtoNP;
import rs.raf.rafnews.service.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategoryNewsCMS(@PathParam("id") int id) {
        List<ResponseNewsDtoCMS> newsList = newsService.findByCategoryIdCMS(id);
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsCMS(@PathParam("id") int id) {
        ResponseNewsDtoCMS news = newsService.findByIdCMS(id);
        return Response.ok(news).build();
    }

    @GET
    @Path("/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompleteNews(@PathParam("id") int id) {
        ResponseNewsFullDtoNP news = newsService.findCompleteNewsById(id);
        return Response.ok(news).build();
    }

    @GET
    @Path("/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatest() {
        List<ResponseNewsDtoNP> newsList = newsService.findLatest();
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/trending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrending() {
        List<ResponseNewsDtoNP> newsList = newsService.findTrending();
        return Response.ok(newsList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNewsCMS() {
        List<ResponseNewsDtoCMS> newsList = newsService.findAllCMS();
        return Response.ok(newsList).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(@Valid RequestNewsDto requestNewsDto) {
        newsService.insertByDto(requestNewsDto);
        return Response.status(Response.Status.CREATED)
                .entity("CREATED")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNews(@PathParam("id") int id, RequestNewsDto updatedNews) {
        updatedNews.setId(id);
        newsService.update(updatedNews);
        return Response.ok("UPDATED").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteNews(@PathParam("id") int id) {
        newsService.deleteById(id);
        return Response.noContent().build();
    }
}
