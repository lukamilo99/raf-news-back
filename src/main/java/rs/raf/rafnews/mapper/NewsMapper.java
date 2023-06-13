package rs.raf.rafnews.mapper;

import rs.raf.rafnews.dto.news.ResponseNewsDto;
import rs.raf.rafnews.dto.news.ResponseNewsFullDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper {

    public ResponseNewsDto mapToResponseNewsDto(ResultSet resultSet, String category, String author) {
        try {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String content = resultSet.getString("content");
            Date creationDate = resultSet.getDate("creation_date");
            return new ResponseNewsDto(id, title, content, author, category, creationDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
