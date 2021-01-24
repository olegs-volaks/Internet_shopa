package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
@Data
public class ProductCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory() {
    }
}
