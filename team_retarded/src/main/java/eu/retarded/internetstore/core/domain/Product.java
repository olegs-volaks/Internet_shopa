package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //private Long categoryId=category.getId();

    public Product(String name, String description, double price, int count) {
        this.name = name;
        this.description = description;
        BigDecimal tmp = new BigDecimal(Double.toString(price));
        this.price = tmp.setScale(2, RoundingMode.DOWN);
        this.count=count;
    }

    public Product() {
    }
}

