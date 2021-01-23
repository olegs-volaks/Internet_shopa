package eu.retarded.internetstore.core.domain;

import java.util.Objects;

public class ProductCategory {

    private Long id;
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory listProductCategory = (ProductCategory) o;
        return id.equals(listProductCategory.id) &&
                Objects.equals(name, listProductCategory.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
