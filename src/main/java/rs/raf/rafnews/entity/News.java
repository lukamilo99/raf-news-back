package rs.raf.rafnews.entity;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class News {

    private int id;
    private String title;
    private String content;
    private Date creationDate;
    private int numberOfVisits;
    private Category category;
    private User author;
    private List<Tag> tagList;
    private List<Comment> commentList;
}
