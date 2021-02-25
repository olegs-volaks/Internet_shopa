package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.requests.user.RegisterUserRequest;
import eu.retarded.internetstore.core.responses.user.RegisterUserResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private AddUserService addUserService;

    @Transactional
    public RegisterUserResponse execute(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RegisterUserResponse(errors);
        }
        AddUserRequest addUserRequest = new AddUserRequest(request.getUserName(), request.getPassword(), request.getPasswordConfirm(), request.getName(),
                request.getSurname(), request.getEmail(), Collections.singleton(1L));
        User user = addUserService.execute(addUserRequest).getUser();
        return new RegisterUserResponse(userRepository.save(user));
    }

}
