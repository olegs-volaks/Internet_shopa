package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "region")
    private String region;

    public Delivery(String title, String region, double price) {
        this.title = title;
        this.region = region;
        BigDecimal tmp = new BigDecimal(Double.toString(price));
        this.price = tmp.setScale(2, RoundingMode.DOWN);
    }

    public Delivery() {
    }
}
