package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersDatabase {

    public Long add(User user);

    public boolean delete(Long id);

    public Optional<User> getUserById(Long id);

    public List<User> getList();


}
