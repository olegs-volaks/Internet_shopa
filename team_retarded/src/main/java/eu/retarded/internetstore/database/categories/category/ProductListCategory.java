package eu.retarded.internetstore.database.categories.category;

import eu.retarded.internetstore.core.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ProductListCategory implements ListCategory<Product> {

    private long id;
    private String name;
    private final List<Product> products = new ArrayList<>();

    public ProductListCategory(String name) {
        this.name = name;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Product> getList() {
        return products;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public boolean remove(Product product) {
        products.remove(product);
        return true;
    }

    @Override
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
        ProductListCategory listProductCategory = (ProductListCategory) o;
        return id == listProductCategory.id &&
                Objects.equals(name, listProductCategory.name) &&
                Objects.equals(products, listProductCategory.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }
}
