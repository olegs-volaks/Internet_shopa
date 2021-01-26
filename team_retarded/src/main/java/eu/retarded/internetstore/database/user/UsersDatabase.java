package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UsersDatabase {

    Long add(User user);

    boolean delete(Long id);

    Optional<User> getUserById(Long id);

    List<User> getList();

    void clear();

    boolean isExist(Long id);


}
