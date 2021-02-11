package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetCartListResponse extends CoreResponse {

    private final List<Cart> cartList;


    public GetCartListResponse(List<CoreError> errors, List<Cart> cartList) {
        super(errors);
        this.cartList = cartList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }
}
