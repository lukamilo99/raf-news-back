package rs.raf.rafnews.repository.impl;

import rs.raf.rafnews.builder.impl.CategoryBuilder;
import rs.raf.rafnews.database.DatabaseUtil;
import rs.raf.rafnews.entity.Category;
import rs.raf.rafnews.exception.CategoryNotFoundException;
import rs.raf.rafnews.repository.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Category findById(int id) {
        String query = "SELECT * FROM Category WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new CategoryNotFoundException("Category with id: " + id + " not found.");
            }
            else {
                return new CategoryBuilder()
                        .setId(resultSet.getInt("id"))
                        .setDescription(resultSet.getString("description"))
                        .setName(resultSet.getString("name"))
                        .build();
            }
        } catch (SQLException | CategoryNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM Category";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category =  new CategoryBuilder()
                        .setId(resultSet.getInt("id"))
                        .setDescription(resultSet.getString("description"))
                        .setName(resultSet.getString("name"))
                        .build();
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public int insert(Category object) {
        String query = "INSERT INTO Category (name, description) values (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            else return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM Category WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Delete successful");
            } else {
                System.out.println("Delete failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void update(Category object) {
        String query = "UPDATE Category SET name = ?, description = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setInt(3, object.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new CategoryNotFoundException("Category with id: " + object.getId() + " not found.");
            }
        } catch (SQLException | CategoryNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }
}
