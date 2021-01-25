package eu.retarded.internetstore.core.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Delivery {

    private Long id;
    private String title;
    private BigDecimal price;
    private String region;

    public Delivery(String title, String region, double price) {
        this.title = title;
        this.region = region;
        BigDecimal tmp = new BigDecimal(Double.toString(price));
        this.price = tmp.setScale(2, RoundingMode.DOWN);
    }

    public Delivery() {
    }
}
