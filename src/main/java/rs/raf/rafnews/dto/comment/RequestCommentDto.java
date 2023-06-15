package rs.raf.rafnews.dto.comment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestCommentDto {

    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    private String content;
    private int userId;
    private int newsId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
