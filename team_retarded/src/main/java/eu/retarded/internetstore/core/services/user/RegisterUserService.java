package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.user.RegisterUserRequest;
import eu.retarded.internetstore.core.responses.user.RegisterUserResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.database.RoleRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Validator validator;

    @Autowired
    private AddCartService addCartService;

    public RegisterUserResponse execute(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RegisterUserResponse(errors);
        }
        if (userRepository.existsByUserName(request.getUserName())) {
            return new RegisterUserResponse(errors);
        }
        Cart cart = addCartService.execute(new AddCartRequest()).getCart();
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setRoles(Collections.singleton(roleRepository.getOne(1L)));
        user.setCart(cart);
        user.setStatus(1);
        return new RegisterUserResponse(userRepository.save(user));
    }

}
