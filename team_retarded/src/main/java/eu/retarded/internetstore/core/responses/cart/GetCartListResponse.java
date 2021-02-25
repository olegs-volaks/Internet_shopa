package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.GetCartListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetCartListResponse extends CoreResponse<GetCartListRequest> {

    private Page<Cart> cartPage;
    private List<Cart> cartList;


    public GetCartListResponse(Set<ConstraintViolation<GetCartListRequest>> errors) {
        super(errors);
    }

    public GetCartListResponse(Page<Cart> cartPage, List<Cart> cartList) {
        this.cartPage = cartPage;
        this.cartList = cartList;
    }

    public Page<Cart> getCartPage() {
        return cartPage;
    }

    public List<Cart> getCartList() {
        return cartList;
    }
}
