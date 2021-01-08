package eu.retarded.internetstore.database.categories.category;

import eu.retarded.internetstore.core.domain.Product;

import java.util.Objects;

public class ProductListDelivery  implements  ListDelivery<Product> {

    private String title;
    private String region;
    private int price;

    public ProductListDelivery(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle() {
        this.title = title;

    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public void setRegion() {
        this.region = region;

    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice() {
        this.price = price;

    }

    @Override
    public String toString() {
        return "ProductListDelivery{" +
                "title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductListDelivery that = (ProductListDelivery) o;
        return price == that.price && title.equals(that.title) && region.equals(that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, region, price);
    }
}
