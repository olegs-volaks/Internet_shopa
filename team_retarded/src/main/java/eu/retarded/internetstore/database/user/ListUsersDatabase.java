package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.Basket;
import eu.retarded.internetstore.core.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUsersDatabase implements UsersDatabase {


    private final List<User> usersDatabase = new ArrayList<>();
    private Long id =0L;

    @Override
    public Long add(User user) {
        id++;
        user.setId(id);
        String role = "Guest";
        user.setRole(role);
        usersDatabase.add(user);
        Basket usersBasket = new Basket(user);
        return id;
    }

    @Override
    public boolean delete(Long id) {
        return usersDatabase.removeIf(x -> x.getId().equals(id));
    }


}
