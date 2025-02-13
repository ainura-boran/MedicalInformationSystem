package factory.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ModelFactory<T> {
    T create(ResultSet rs) throws SQLException;
}
