package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.User;

public interface UsersDatabase {

    public Long add(User user);

    public boolean delete(Long id);
}
