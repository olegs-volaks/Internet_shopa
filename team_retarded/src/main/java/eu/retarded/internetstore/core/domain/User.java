package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    //private Basket usersBasket;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Integer role;

    @Column(name = "basket_id")
    private Long basketId;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
        //usersBasket = new Basket();
    }

    public User() {
    }
}
