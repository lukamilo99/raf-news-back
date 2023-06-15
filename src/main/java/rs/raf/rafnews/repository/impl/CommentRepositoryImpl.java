package rs.raf.rafnews.repository.impl;

import rs.raf.rafnews.builder.impl.CommentBuilder;
import rs.raf.rafnews.builder.impl.UserBuilder;
import rs.raf.rafnews.database.DatabaseUtil;
import rs.raf.rafnews.dto.comment.RequestCommentDto;
import rs.raf.rafnews.entity.Comment;
import rs.raf.rafnews.repository.CommentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    @Override
    public List<Comment> findCommentsByNewsId(int id) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT id, content, creation_date, user_id FROM Comment WHERE news_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new CommentBuilder()
                        .setId(resultSet.getInt("id"))
                        .setContent(resultSet.getString("content"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setAuthor(new UserBuilder()
                                .setId(resultSet.getInt("user_id"))
                                .build())
                        .build();
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseUtil.closeResultSet(resultSet);
            DatabaseUtil.closeStatement(statement);
            DatabaseUtil.closeConnection(connection);
        }
    }

    @Override
    public void deleteByNewsId(int newsId) {
        String query = "DELETE FROM Comment WHERE news_id = ?";
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
    public void insert(RequestCommentDto requestCommentDto) {
        String query = "INSERT INTO Comment (content, creation_date, user_id, news_id) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, requestCommentDto.getContent());
            statement.setDate(2, new Date(System.currentTimeMillis()));
            statement.setInt(3, requestCommentDto.getUserId());
            statement.setInt(4, requestCommentDto.getNewsId());
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
}
