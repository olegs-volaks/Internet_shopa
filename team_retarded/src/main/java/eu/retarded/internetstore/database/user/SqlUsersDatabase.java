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
        jdbcTemplate.update("INSERT INTO user (login,pasword ) VALUES (?,?)", user.getLogin(),
                user.getPassword());
        return jdbcTemplate.queryForObject("SELECT id FROM user WHERE id=(SELECT max(id) FROM products)",
                Long.class);
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id=?",id)==1;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        User user;
        try {
            user=jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?",new UserMapper(), id);
        }catch (IncorrectResultSizeDataAccessException exception){
            user=null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getList() {
        return jdbcTemplate.query("SELECT * FROM user", new UserMapper());
    }

}
