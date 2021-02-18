package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.GetCartListRequest;
import eu.retarded.internetstore.core.responses.cart.GetCartListResponse;
import eu.retarded.internetstore.database.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
public class GetCartListService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    Validator validator;

    @Transactional
    public GetCartListResponse execute(GetCartListRequest request) {
        Set<ConstraintViolation<GetCartListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetCartListResponse(errors, null, null);
        }


        List<Cart> carts;
        if (request.getPageable()==null){
            carts = cartRepository.findAll();
            return new GetCartListResponse(null, null, carts);
        }
        Page<Cart> cartsPage = cartRepository.findAll(request.getPageable());
        return new GetCartListResponse(null, cartsPage, null);
    }
}
