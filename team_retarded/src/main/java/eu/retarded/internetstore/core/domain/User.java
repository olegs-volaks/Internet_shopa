package eu.retarded.internetstore.core.domain;


public class User {


    private Basket usersBasket;

    private Long id;
    private String login;
    private String password;
    private String role;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        usersBasket=new Basket();
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Basket getUsersBasket() {
        return usersBasket;
    }
}
