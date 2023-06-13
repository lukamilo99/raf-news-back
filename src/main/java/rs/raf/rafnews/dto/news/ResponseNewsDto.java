package rs.raf.rafnews.dto.news;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseNewsDto {

    private int id;
    private String title;
    private String content;
    private String author;
    private String category;
    private Date creationDate;

    public ResponseNewsDto(int id, String title, String content, String author, String category, Date creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.creationDate = creationDate;
    }
}
