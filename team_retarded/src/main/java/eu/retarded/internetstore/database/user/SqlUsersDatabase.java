package eu.retarded.internetstore.database.user;


import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.domain.row_mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

//@Component
public class SqlUsersDatabase implements UsersDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long add(User user) {
        jdbcTemplate.update("INSERT INTO users (login,password ) VALUES (?,?)", user.getLogin(),
                user.getPassword());
        return jdbcTemplate.queryForObject("SELECT id FROM users WHERE id=(SELECT max(id) FROM products)",
                Long.class);
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) == 1;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        User user;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserMapper(), id);
        } catch (IncorrectResultSizeDataAccessException exception) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getList() {
        return jdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM users");
    }

    @Override
    public boolean isExist(Long id) {
        return getUserById(id).isPresent();
    }

}
