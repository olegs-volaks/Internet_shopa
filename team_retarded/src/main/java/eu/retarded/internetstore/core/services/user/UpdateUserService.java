package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserResponse;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UpdateUserService {

    @Autowired
    private UsersDatabase usersDatabase;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateUserResponse execute(UpdateUserRequest request) {
        Set<ConstraintViolation<UpdateUserRequest>> errors = validator.validate(request);
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
