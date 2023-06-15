package rs.raf.rafnews.builder.impl;

import rs.raf.rafnews.builder.AbstractBuilder;
import rs.raf.rafnews.entity.Comment;
import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.entity.User;

import java.sql.Date;

public class CommentBuilder extends AbstractBuilder<Comment> {

    @Override
    protected Comment createObject() {
        return new Comment();
    }

    public CommentBuilder setId(int id) {
        object.setId(id);
        return this;
    }

    public CommentBuilder setContent(String content) {
        object.setContent(content);
        return this;
    }

    public CommentBuilder setCreationDate(Date creationDate) {
        object.setCreationDate(creationDate);
        return this;
    }

    public CommentBuilder setAuthor(User author) {
        object.setAuthor(author);
        return this;
    }

    public CommentBuilder setNews(News news) {
        object.setNews(news);
        return this;
    }
}
