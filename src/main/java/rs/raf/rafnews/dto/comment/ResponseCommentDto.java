package rs.raf.rafnews.dto.comment;

import java.sql.Date;

public class ResponseCommentDto {

    private int id;
    private String content;
    private Date creationDate;
    private String author;

    public ResponseCommentDto(int id, String content, Date creationDate, String author) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
