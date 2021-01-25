package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        String login = rs.getString("login");
        String password = rs.getString("password");
        Integer role = rs.getInt("role");
        user.setId(rs.getLong("id"));
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}

