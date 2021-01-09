package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDatabase {


    private final List<User> usersDatabase = new ArrayList<>();
    private Long id =0L;
    private final String role = "Guest";


    public Long add(User user) {
        id++;
        user.setId(id);
        user.setRole(role);
        usersDatabase.add(user);
        return id;
    }


    public boolean delete(Long id) {
        usersDatabase.removeIf(x -> x.getId().equals(id));
        return true;
    }



}
