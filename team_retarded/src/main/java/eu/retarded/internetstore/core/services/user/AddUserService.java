package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.validators.user.AddUserValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {

    @Autowired
    private UsersDatabase db;
    @Autowired
    private AddUserValidator validator;

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }
        return new AddUserResponse(db.add(new User(request.getLogin(), request.getPassword())));
    }
}
