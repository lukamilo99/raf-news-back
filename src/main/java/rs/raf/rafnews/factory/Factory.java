package rs.raf.rafnews.factory;

import java.sql.ResultSet;

public interface Factory<T> {
    T create(ResultSet resultSet);
}
