package eu.retarded.internetstore.core.domain;

import lombok.Data;

@Data
public class User {

    private Basket usersBasket;

    private Long id;
    private String login;
    private String password;
    private String role;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        usersBasket = new Basket();
    }

    public User() {
    }
}
