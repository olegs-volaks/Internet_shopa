package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.validators.user.AddUserValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AddUserService {

    @Autowired
    private UsersDatabase db;

    @Autowired
    private AddUserValidator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        return new AddUserResponse(db.add(user));
    }
}
