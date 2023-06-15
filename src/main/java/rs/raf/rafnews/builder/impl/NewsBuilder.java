package rs.raf.rafnews.builder.impl;

import rs.raf.rafnews.builder.AbstractBuilder;
import rs.raf.rafnews.entity.*;

import java.sql.Date;
import java.util.List;

public class NewsBuilder extends AbstractBuilder<News> {

    @Override
    protected News createObject() {
        return new News();
    }

    public NewsBuilder setId(int id) {
        object.setId(id);
        return this;
    }

    public NewsBuilder setTitle(String title) {
        object.setTitle(title);
        return this;
    }

    public NewsBuilder setContent(String content) {
        object.setContent(content);
        return this;
    }

    public NewsBuilder setCreationDate(Date creationDate) {
        object.setCreationDate(creationDate);
        return this;
    }

    public NewsBuilder setNumberOfVisits() {
        object.setNumberOfVisits(0);
        return this;
    }

    public NewsBuilder setCategory(Category category) {
        object.setCategory(category);
        return this;
    }

    public NewsBuilder setAuthor(User author) {
        object.setAuthor(author);
        return this;
    }

    public NewsBuilder setTagList(List<Tag> tagList) {
        object.setTagList(tagList);
        return this;
    }

    public NewsBuilder setCommentList(List<Comment> commentList) {
        object.setCommentList(commentList);
        return this;
    }
}
