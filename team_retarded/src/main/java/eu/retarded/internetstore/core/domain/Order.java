package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
public class Order {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "client_surname")
    private String surname;

    @Column(name = "client_address")
    private String address;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public Order(String name, String surname, String address, Cart cart, Delivery delivery, User user,
                 BigDecimal totalPrice) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.cart = cart;
        this.delivery = delivery;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

}
