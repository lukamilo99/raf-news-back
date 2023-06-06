package rs.raf.rafnews.factory.impl;

import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.factory.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory implements Factory<User> {

    @Override
    public User create(ResultSet resultSet) {
        try {
            return new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getBoolean("status"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
