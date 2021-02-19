package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.requests.user.AddProductToUserCartRequest;
import eu.retarded.internetstore.core.responses.user.AddProductToUserCartResponse;
import eu.retarded.internetstore.core.services.cart.UpdateCartService;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

@Service
public class AddProductToUserCartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UpdateCartService updateCartService;

    @Autowired
    private Validator validator;

    @Transactional
    public AddProductToUserCartResponse execute(AddProductToUserCartRequest request) {
        User activeUser = userRepository.getOne(request.getUserId());
        UpdateCartRequest updateCartRequest = new UpdateCartRequest(activeUser.getCart().getId(),
                request.getProductId(), request.getCount());
        Cart cart = updateCartService.execute(updateCartRequest).getCart();
        return new AddProductToUserCartResponse(cart);
    }
}
