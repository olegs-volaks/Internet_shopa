package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.requests.user.GetUsersListRequest;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.responses.user.DeleteUserResponse;
import eu.retarded.internetstore.core.responses.user.GetUserByIdResponse;
import eu.retarded.internetstore.core.responses.user.GetUsersListResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.database.RoleRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }

    public AddUserResponse addUser(AddUserRequest request) {
        Set<ConstraintViolation<AddUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }
        if (userRepository.existsByUserName(request.getUserName())) {
            return new AddUserResponse(errors);
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
        return new AddUserResponse(userRepository.save(user));
    }

    public GetUserByIdResponse getUserById(GetUserByIdRequest request) {
        Set<ConstraintViolation<GetUserByIdRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUserByIdResponse(errors);
        }
        return new GetUserByIdResponse(userRepository.findById(request.getUserId()).get());
    }

    public GetUsersListResponse getUsersList(GetUsersListRequest request) {
        return new GetUsersListResponse(null, userRepository.findAll(request.getPageable()));
    }

    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        Set<ConstraintViolation<DeleteUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteUserResponse(errors);
        }
        userRepository.deleteById(request.getUserId());
        return new DeleteUserResponse(true);
    }
}
