package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.requests.cart.GetCartListRequest;
import eu.retarded.internetstore.core.responses.cart.GetCartListResponse;
import eu.retarded.internetstore.database.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetCartListService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public GetCartListResponse execute(GetCartListRequest request) {
        return new GetCartListResponse(null, cartRepository.findAll(request.getPageable()));
    }
}
