package rs.raf.rafnews.dto.news;

import lombok.Getter;
import lombok.Setter;
import rs.raf.rafnews.dto.comment.ResponseCommentDto;
import rs.raf.rafnews.entity.Tag;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ResponseNewsFullDto {

    private int id;
    private String title;
    private String content;
    private String author;
    private String category;
    private Date creationDate;
    private List<Tag> tags;
    private List<ResponseCommentDto> comments;

    public ResponseNewsFullDto(int id, String title, String content, String author, String category, Date creationDate, List<Tag> tags, List<ResponseCommentDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.creationDate = creationDate;
        this.tags = tags;
        this.comments = comments;
    }
}
