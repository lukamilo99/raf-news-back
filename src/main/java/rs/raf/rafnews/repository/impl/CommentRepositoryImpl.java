package rs.raf.rafnews.repository.impl;

import rs.raf.rafnews.database.DatabaseUtil;
import rs.raf.rafnews.dto.comment.RequestCommentDto;
import rs.raf.rafnews.dto.comment.ResponseCommentDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
import rs.raf.rafnews.repository.CommentRepository;
import rs.raf.rafnews.repository.UserRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    @Inject
    private UserRepository userRepository;

    @Override
    public List<ResponseCommentDto> findCommentsByNewsId(int id) {
        List<ResponseCommentDto> comments = new ArrayList<>();
        String query = "SELECT * FROM Comment WHERE news_id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int commentId = resultSet.getInt("id");
                String content = resultSet.getString("content");
                Date creationDate = resultSet.getDate("creation_date");
                int userId = resultSet.getInt("user_id");
                ResponseUserDto userDto = userRepository.findById(userId);
                String author = userDto.getFirstname() + " " + userDto.getLastname();
                ResponseCommentDto comment = new ResponseCommentDto(commentId, content, creationDate, author);
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
