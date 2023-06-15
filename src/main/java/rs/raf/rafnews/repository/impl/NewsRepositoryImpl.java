package rs.raf.rafnews.repository.impl;

import rs.raf.rafnews.builder.impl.CategoryBuilder;
import rs.raf.rafnews.builder.impl.NewsBuilder;
import rs.raf.rafnews.builder.impl.UserBuilder;
import rs.raf.rafnews.database.DatabaseUtil;
import rs.raf.rafnews.dto.news.RequestNewsDto;
import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.entity.Tag;
import rs.raf.rafnews.exception.NewsNotFoundException;
import rs.raf.rafnews.repository.*;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

    @Inject
    private TagRepository tagRepository;
    @Inject
    private CommentRepository commentRepository;

    @Override
    public News findById(int id) {
        String query = "SELECT id, title, content, creation_date, category_id, user_id FROM News WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new NewsNotFoundException("News with id: " + id + " not found.");
            } else {
                return new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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
        String query = "SELECT id, title, CONCAT(SUBSTRING(content, 1, 30), '...') AS content, creation_date, category_id, user_id FROM News";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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
    public List<News> findWithPagination(int page) {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT id, title, CONCAT(SUBSTRING(content, 1, 30), '...') AS content, creation_date, category_id, user_id FROM News LIMIT 6 OFFSET ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, (page - 1) * 6);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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
    public List<News> findLatest() {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT id, title, CONCAT(SUBSTRING(content, 1, 30), '...') AS content, creation_date, category_id, user_id FROM News ORDER BY creation_date ASC LIMIT 10";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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
    public News findCompleteById(int id) {
        String query = "SELECT * FROM News WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new NewsNotFoundException("News with id: " + id + " not found.");
            }
            else {
                return new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .setTagList(findTagsByNewsId(id))
                        .setCommentList(new ArrayList<>())
                        .build();
            }
        } catch (SQLException | NewsNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            incrementNewsNumberOfVisits(id);
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void incrementNewsNumberOfVisits(int id) {
        String query = "UPDATE News SET number_of_visits = number_of_visits + 1 WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new NewsNotFoundException("News with id: " + id + " not found.");
            }
        } catch (SQLException | NewsNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<News> findTrending() {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT id, title, CONCAT(SUBSTRING(content, 1, 30), '...') AS content, creation_date, category_id, user_id FROM News ORDER BY number_of_visits DESC LIMIT 10";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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
    public List<News> findByTagId(int tagId) {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT id, title, CONCAT(SUBSTRING(content, 1, 30), '...') AS content, creation_date, category_id, user_id FROM News INNER JOIN News_tag ON News.id = News_tag.news_id WHERE News_tag.tag_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, tagId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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
    public void insert(RequestNewsDto requestNewsDto) {
        String query = "INSERT INTO News (title, content, creation_date, number_of_visits, category_id, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, requestNewsDto.getTitle());
            statement.setString(2, requestNewsDto.getContent());
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setInt(4, 0);
            statement.setInt(5, requestNewsDto.getCategoryId());
            statement.setInt(6, requestNewsDto.getUserId());
            int rowsInserted = statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                insertTags(requestNewsDto.getTags(), generatedKeys.getInt(1));
            }

            if (rowsInserted > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertNewsTag(int newsId, int tagId) {
        String query = "INSERT INTO News_tag (news_id, tag_id) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        if(doesNewsTagExists(newsId, tagId)) {
            return;
        }

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newsId);
            statement.setInt(2, tagId);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteNewsTag(int newsId) {
        String query = "DELETE FROM news_tag WHERE news_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newsId);
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
    public void deleteById(int id) {
        String query = "DELETE FROM News WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            deleteNewsTag(id);
            commentRepository.deleteByNewsId(id);
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
    public void update(RequestNewsDto object) {
        String query = "UPDATE News SET title = ?, content = ?, category_id = ?, user_id = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getContent());
            statement.setInt(3, object.getCategoryId());
            statement.setInt(4, object.getUserId());
            statement.setInt(5, object.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Update successful");
            } else {
                throw new NewsNotFoundException("News with id: " + object.getId() + " not found.");
            }
        } catch (SQLException | NewsNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public List<News> findByCategoryId(int categoryId) {
        List<News> newsList = new ArrayList<>();
        String query = "SELECT id, title, CONCAT(SUBSTRING(content, 1, 30), '...') AS content, creation_date, category_id, user_id FROM News WHERE category_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, categoryId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new NewsBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setCategory(new CategoryBuilder()
                                .setId(resultSet.getInt("category_id"))
                                .build())
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
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

    private void insertTags(List<String> tagList, int newsId) {
        for (String tag: tagList) {
            int tagId = tagRepository.findByName(tag);
            if (tagId == - 1) {
                int createdTagId = tagRepository.insert(new Tag(tag));
                insertNewsTag(newsId, createdTagId);
            }
            else {
                insertNewsTag(newsId, tagId);
            }
        }
    }

    private List<Tag> findTagsByNewsId(int newsId) {
        List<Tag> tags = new ArrayList<>();
        String query = "SELECT Tag.id, Tag.name FROM Tag INNER JOIN News_tag ON Tag.id = News_tag.tag_id WHERE News_tag.news_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newsId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Tag tag = new Tag(id, name);
                tags.add(tag);
            }
            return tags;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    private boolean doesNewsTagExists(int newsId, int tagId) {
        String query = "SELECT * FROM News_tag WHERE news_id = ? AND tag_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, newsId);
            statement.setInt(2, tagId);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }
}
