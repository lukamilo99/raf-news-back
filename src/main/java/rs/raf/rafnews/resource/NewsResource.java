package rs.raf.rafnews.resource;

import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsFullDto;
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
    @Path("/public/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategoryNews(@PathParam("id") int id) {
        List<ResponseNewsDto> newsList = newsService.findByCategoryId(id);
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/public/tag/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTagNews(@PathParam("id") int id) {
        List<ResponseNewsDto> newsList = newsService.findByTagId(id);
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@PathParam("id") int id) {
        ResponseNewsDto news = newsService.findById(id);
        return Response.ok(news).build();
    }

    @GET
    @Path("/public/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompleteNews(@PathParam("id") int id) {
        ResponseNewsFullDto news = newsService.findCompleteById(id);
        return Response.ok(news).build();
    }

    @GET
    @Path("/public/latest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLatest() {
        List<ResponseNewsDto> newsList = newsService.findLatest();
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/public/trending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrending() {
        List<ResponseNewsDto> newsList = newsService.findTrending();
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/public/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNews() {
        List<ResponseNewsDto> newsList = newsService.findAll();
        return Response.ok(newsList).build();
    }

    @GET
    @Path("/public/page/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWithPagination(@PathParam("page") int page) {
        List<ResponseNewsDto> newsList = newsService.findWithPagination(page);
        return Response.ok(newsList).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(@Valid RequestNewsDto requestNewsDto) {
        System.out.println(requestNewsDto.getTags().listIterator().next());
        newsService.insert(requestNewsDto);
        return Response.status(Response.Status.CREATED)
                .entity("CREATED")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNews(@PathParam("id") int id, @Valid RequestNewsDto updatedNews) {
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
