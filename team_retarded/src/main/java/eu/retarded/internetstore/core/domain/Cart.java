package eu.retarded.internetstore.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "products_in_cart",
            joinColumns = {@JoinColumn(name = "cart_id")})
    @Column(name = "product_count")
    @MapKeyJoinColumn(name = "product_id")
    private Map<Product, Integer> products;

    @Column(name = "status")
    private int status;

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            BigDecimal price = entry.getKey().getPrice();
            int count = entry.getValue();
            price = price.multiply(BigDecimal.valueOf(count));
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }

    public static BigDecimal getPrice(Map<Product, Integer> productMap) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            BigDecimal price = entry.getKey().getPrice();
            int count = entry.getValue();
            price = price.multiply(BigDecimal.valueOf(count));
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }
}
