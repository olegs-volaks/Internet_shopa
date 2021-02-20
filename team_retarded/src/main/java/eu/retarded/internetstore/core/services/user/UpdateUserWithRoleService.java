package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserWithRoleRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserWithRoleResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.RoleRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UpdateUserWithRoleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateUserWithRoleResponse execute(UpdateUserWithRoleRequest request) {
        Set<ConstraintViolation<UpdateUserWithRoleRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateUserWithRoleResponse(errors);
        }
        User activeUser = userRepository.getOne(request.getId());
        activeUser.setName(request.getName());
        activeUser.setSurname(request.getSurname());
        activeUser.setEmail(request.getEmail());
        List<Role> roleList = roleRepository.findAllById(Arrays.asList(request.getRolesId()));
        activeUser.setRoles(new HashSet<>(roleList));
        return new UpdateUserWithRoleResponse(activeUser);
    }
}

