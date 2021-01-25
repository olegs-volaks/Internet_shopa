package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
@Data
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }
}
