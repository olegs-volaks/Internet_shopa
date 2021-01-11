package eu.retarded.internetstore.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Delivery {

    private String title;
    private BigDecimal price;
    private String region;

    public Delivery(String title,String region, double price) {
        this.title = title;
        this.region = region;
        BigDecimal tmp = new BigDecimal(Double.toString(price));
        this.price = tmp.setScale(2, RoundingMode.DOWN);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", region='" + region + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return title.equals(delivery.title) && price.equals(delivery.price) && region.equals(delivery.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, region);
    }
}
