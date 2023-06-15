package rs.raf.rafnews.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
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

    public News(int id, String title, String content, Date creationDate, int numberOfVisits) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.numberOfVisits = numberOfVisits;
    }

    public News() {

    }
}
