package eu.retarded.internetstore.core.domain;

import lombok.Data;

@Data
public class ProductCategory {

    private Long id;
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory() {
    }
}
