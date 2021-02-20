package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UpdateUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateUserResponse execute(UpdateUserRequest request) {
        Set<ConstraintViolation<UpdateUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateUserResponse(errors);
        }
        User activeUser = userRepository.getOne(request.getId());
        activeUser.setUserName(request.getLogin());
        activeUser.setPassword(request.getPassword());
        activeUser.setName(request.getName());
        activeUser.setSurname(request.getSurname());
        activeUser.setEmail(request.getEmail());
        activeUser.setCart(cartRepository.getOne(request.getCartId()));
        return new UpdateUserResponse(activeUser);
    }
}
