package rs.raf.rafnews.factory.impl;

import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.factory.Factory;

import java.sql.ResultSet;

public class NewsFactory implements Factory<News> {

    @Override
    public News create(ResultSet resultSet) {
        return null;
    }
}