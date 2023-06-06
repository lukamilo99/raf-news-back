package rs.raf.rafnews.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Comment {

    private int id;
    private String content;
    private Date creationDate;
    private User author;
    private News news;
}
