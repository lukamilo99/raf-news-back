package rs.raf.rafnews.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Comment {

    private int id;
    private String content;
    private Date creationDate;
    private User author;
    private News news;
}
