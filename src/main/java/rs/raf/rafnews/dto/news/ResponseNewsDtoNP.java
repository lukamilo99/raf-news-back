package rs.raf.rafnews.dto.news;

import java.util.Date;

public class ResponseNewsDtoNP {

    private int id;
    private String title;
    private String content;
    private String category;
    private Date creationDate;

    public int getId() {
        return id;
    }

    public ResponseNewsDtoNP(int id, String title, String content, String category, Date creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.creationDate = creationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
