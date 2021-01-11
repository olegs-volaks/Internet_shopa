package eu.retarded.internetstore.core.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class Basket {

    @Autowired
    User user;
    private Long id;
    private final HashMap<Long, Integer> basket = new HashMap<>();

    public Basket(User user) {
        this.user = user;
    }

    public void add(Long productID, Integer quantity) {
        basket.put(productID, quantity);
    }

    public boolean removeProduct(Long productID) {
         basket.remove(productID);
        return !basket.containsValue(productID);
    }

    public boolean changeQuantity(Long productID, Integer quantity) {
         basket.replace(productID, quantity);
        return basket.containsValue(quantity);
    }

}
