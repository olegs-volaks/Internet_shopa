package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.UpdateUserResponse;
import eu.retarded.internetstore.core.services.validators.user.UpdateUserValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateUserService {

    @Autowired
    private UsersDatabase usersDatabase;

    @Autowired
    private UpdateUserValidator validator;

    public UpdateUserResponse execute(UpdateUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateUserResponse(errors);
        }
        long id = request.getId();
        User oldUser = usersDatabase.getUserById(id).get();
        User resultUser = new User();
        resultUser.setId(id);
        resultUser.setLogin(oldUser.getLogin());
        resultUser.setPassword(oldUser.getPassword());
        resultUser.setRole(request.getRole());
        resultUser.setName(request.getName());
        resultUser.setSurname(request.getSurname());
        resultUser.setEmail(request.getEmail());
        usersDatabase.updateUser(resultUser);
        return new UpdateUserResponse(id);
    }
}
