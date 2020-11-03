package application.items;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private final long id;
    private String productName;
    private String specification;
    private BigDecimal price;

    public Product(long id, String productName, String specification, BigDecimal price) {
        this.id = id;
        this.productName = productName;
        this.specification = specification;
        this.price = price;
    }

    public long getId() {
        return id;
    }


    public String getProductName() {
        return productName;
    }

    public void changeProductName(String productName) {
        this.productName = productName;
    }

    public String getSpecification() {
        return specification;
    }

    public void changeSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(specification, product.specification) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, specification, price);
    }
}
