package rs.raf.rafnews.dto.news;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseNewsDtoCMS {

    private int id;
    private String title;
    private String content;
    private String author;
    private Date creationDate;

    public ResponseNewsDtoCMS(int id, String title, String author, Date creationDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
    }

    public ResponseNewsDtoCMS(int id, String title, String content, String author, Date creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
    }
}
