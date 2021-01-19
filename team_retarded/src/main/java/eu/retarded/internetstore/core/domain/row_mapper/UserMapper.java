package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String login = rs.getString("login");
        String password = rs.getString("password");
        String role = rs.getString("role");
        User user = new User(login, password);
        user.setId(rs.getLong("id"));
        return user;
    }
}

