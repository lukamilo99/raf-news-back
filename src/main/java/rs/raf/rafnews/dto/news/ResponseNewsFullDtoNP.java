package rs.raf.rafnews.dto.news;

import rs.raf.rafnews.dto.comment.ResponseCommentDto;
import rs.raf.rafnews.entity.Tag;

import java.util.Date;
import java.util.List;

public class ResponseNewsFullDtoNP {

    private int id;
    private String title;
    private String content;
    private String author;
    private Date creationDate;
    private List<Tag> tags;
    private List<ResponseCommentDto> comments;

    public ResponseNewsFullDtoNP(int id, String title, String content, String author, Date creationDate, List<Tag> tags, List<ResponseCommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
        this.tags = tags;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<ResponseCommentDto> getComments() {
        return comments;
    }

    public void setComments(List<ResponseCommentDto> comments) {
        this.comments = comments;
    }
}
