package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserWithRoleRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserWithRoleResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UpdateUserWithRoleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateUserWithRoleResponse execute(UpdateUserWithRoleRequest request) {
        Set<ConstraintViolation<UpdateUserWithRoleRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateUserWithRoleResponse(errors);
        }
        User activeUser = userRepository.getOne(request.getId());
        activeUser.setUserName(request.getLogin());
        activeUser.setPassword(request.getPassword());
        activeUser.setName(request.getName());
        activeUser.setSurname(request.getSurname());
        activeUser.setEmail(request.getEmail());
        activeUser.setCart(cartRepository.getOne(request.getCartId()));
        activeUser.setRoles(request.getRoles());
        return new UpdateUserWithRoleResponse(activeUser);
    }
}

