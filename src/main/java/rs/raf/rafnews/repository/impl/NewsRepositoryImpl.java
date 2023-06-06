package rs.raf.rafnews.repository.impl;

import rs.raf.rafnews.database.DatabaseUtil;
import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.exception.NewsNotFoundException;
import rs.raf.rafnews.factory.Factory;
import rs.raf.rafnews.repository.NewsRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

    @Inject
    private Factory<News> factory;

    @Override
    public News findById(int id) {
        String query = "SELECT * FROM News WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new NewsNotFoundException("News with id: " + id + " not found.");
            } else {
                return factory.create(resultSet);
            }
        } catch (SQLException | NewsNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<News> findAll() {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT * FROM News";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = factory.create(resultSet);
                newsList.add(news);
            }

            return newsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<News> findAllPagination(int pageNumber, int pageSize) {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT * FROM News LIMIT ? OFFSET ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            int offset = (pageNumber - 1) * pageSize;
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = factory.create(resultSet);
                newsList.add(news);
            }

            return newsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<News> findByCriteria(Criteria criteria) {
        return null;
        // TODO: Implement the logic to retrieve news based on the provided criteria
    }

    @Override
    public News insert(News object) {
        String query = "INSERT INTO News (title, content, creation_date, number_of_visits, category_id, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getContent());
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setInt(4, 0);
            statement.setInt(5, object.getCategory().getId());
            statement.setInt(6, object.getAuthor().getId());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }
            return object;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM News WHERE id = ?";
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
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public News update(News object) {
        String query = "UPDATE News SET title = ?, content = ?, creationDate = ?, numberOfVisits = ?, " +
                "category = ?, author = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getContent());
            statement.setDate(3, object.getCreationDate());
            statement.setInt(4, object.getNumberOfVisits());
            statement.setInt(5, object.getCategory().getId());
            statement.setInt(6, object.getAuthor().getId());
            statement.setInt(7, object.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new NewsNotFoundException("News with id: " + object.getId() + " not found.");
            }
            return object;
        } catch (SQLException | NewsNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public int count() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM News";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }
}
