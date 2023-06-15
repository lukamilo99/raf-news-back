package rs.raf.rafnews.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
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
}
