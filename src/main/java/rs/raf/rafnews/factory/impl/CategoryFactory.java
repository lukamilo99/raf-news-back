package rs.raf.rafnews.factory.impl;

import rs.raf.rafnews.entity.Category;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.factory.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryFactory implements Factory<Category> {

    @Override
    public Category create(ResultSet resultSet) {
        try {
            return new Category(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
