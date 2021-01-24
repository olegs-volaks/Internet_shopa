package eu.retarded.internetstore.core.domain;

import lombok.Data;

import java.util.HashMap;

@Data
public class Basket {


    private final HashMap<Product, Integer> basket = new HashMap<>();

    public boolean add(Product product, Integer quantity) {
        basket.put(product, quantity);
        return basket.containsKey(product);
    }

    public boolean removeProduct(Product product) {
        basket.remove(product);
        return !basket.containsValue(product);
    }

    public boolean changeQuantity(Product product, Integer quantity) {
        basket.replace(product, quantity);
        return basket.containsValue(quantity);
    }

}
