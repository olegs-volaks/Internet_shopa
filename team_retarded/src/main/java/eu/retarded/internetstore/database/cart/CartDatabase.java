package eu.retarded.internetstore.database.cart;

import eu.retarded.internetstore.core.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartDatabase {

    Long add(Cart cart);

    boolean delete(Long id);

    Optional<Cart> getById(Long id);

    List<Cart> getList();

    void updateCart(Cart cart);

    boolean isExist(Long id);
}
