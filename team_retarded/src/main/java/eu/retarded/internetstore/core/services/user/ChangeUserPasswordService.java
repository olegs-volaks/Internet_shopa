package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.responses.user.ChangeUserPasswordResponse;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ChangeUserPasswordService {

    @Autowired
    private UsersDatabase usersDatabase;

    @Autowired
    private Validator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ChangeUserPasswordResponse execute(ChangeUserPasswordRequest request) {
        Set<ConstraintViolation<ChangeUserPasswordRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangeUserPasswordResponse(errors);
        }
        long id = request.getUserId();
        User oldUser = usersDatabase.getUserById(id).get();
        if (passwordEncoder.matches(request.getOldPassword(), oldUser.getPassword())) {
            User resultUser = new User();
            resultUser.setId(id);
            resultUser.setLogin(oldUser.getLogin());
            resultUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
            resultUser.setRole(oldUser.getRole());
            resultUser.setName(oldUser.getName());
            resultUser.setSurname(oldUser.getSurname());
            resultUser.setEmail(oldUser.getEmail());
            usersDatabase.updateUser(resultUser);
            return new ChangeUserPasswordResponse(id);
        } else {
            // todo: добавить проверку пользователя
//            errors.add(new CoreError("Old password", "Incorrect password"));
            return new ChangeUserPasswordResponse(errors);
        }
    }
}
