package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.GetCartListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetCartListResponse extends CoreResponse<GetCartListRequest> {

    private List<Cart> cartList;


    public GetCartListResponse(Set<ConstraintViolation<GetCartListRequest>> errors, List<Cart> cartList) {
        super(errors);
        this.cartList = cartList;
    }

    public List<Cart> getCartList() {
        return cartList;
    }
}
