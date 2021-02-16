package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.GetCartListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetCartListResponse extends CoreResponse<GetCartListRequest> {

    private Page<Cart> cartList;


    public GetCartListResponse(Set<ConstraintViolation<GetCartListRequest>> errors, Page<Cart> cartList) {
        super(errors);
        this.cartList = cartList;
    }

    public Page<Cart> getCartList() {
        return cartList;
    }
}
