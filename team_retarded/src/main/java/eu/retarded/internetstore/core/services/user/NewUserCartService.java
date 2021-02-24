package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.user.NewUserCartRequest;
import eu.retarded.internetstore.core.responses.user.NewUserCartResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewUserCartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddCartService addCartService;

    @Transactional
    public NewUserCartResponse execute(NewUserCartRequest request) {
        User user = userRepository.getOne(request.getUserId());
        Cart oldCart = user.getCart();
        Cart newCart = addCartService.execute(new AddCartRequest()).getCart();
        user.setCart(newCart);
        userRepository.save(user);
        Authentication authentication = new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new NewUserCartResponse(oldCart);
    }
}
