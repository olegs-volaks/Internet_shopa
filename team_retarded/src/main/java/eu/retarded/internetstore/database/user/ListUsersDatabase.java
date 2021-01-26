package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class ListUsersDatabase implements UsersDatabase {

    private final List<User> usersDatabase = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Long add(User user) {
        id++;
        user.setId(id);
        Integer role = 0;
        user.setRole(role);
        usersDatabase.add(user);
        return id;
    }

    @Override
    public boolean delete(Long id) {
        return usersDatabase.removeIf(x -> x.getId().equals(id));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return usersDatabase.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isExist(Long id) {
        return false;
    }
}
