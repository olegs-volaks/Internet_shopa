package eu.retarded.internetstore.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ProductCategory {

    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    public List<Product> getList() {
        return products;
    }

    public void add(Product product) {
        products.add(product);
    }

    public boolean remove(Product product) {
        return products.remove(product);
    }

    public void remove(Predicate<Product> predicate) {
        products.removeIf(predicate);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productsIdList=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory listProductCategory = (ProductCategory) o;
        return id.equals(listProductCategory.id) &&
                Objects.equals(name, listProductCategory.name) &&
                Objects.equals(products, listProductCategory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}
