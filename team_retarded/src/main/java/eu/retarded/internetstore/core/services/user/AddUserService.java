package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.database.RoleRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddUserService {

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

    @Transactional
    public AddUserResponse execute(AddUserRequest request) {
        Set<ConstraintViolation<AddUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }
        if (userRepository.existsByUsername(request.getUserName())) {
            return new AddUserResponse(errors);
        }
        Cart cart = addCartService.execute(new AddCartRequest()).getCart();
        User user = new User();
        user.setUsername(request.getUserName());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        List<Role> roleList = roleRepository.findAllById(request.getRolesId());
        user.setRoles(new HashSet<>(roleList));
        user.setCart(cart);
        user.setStatus(1);
        return new AddUserResponse(userRepository.save(user));
    }
}
