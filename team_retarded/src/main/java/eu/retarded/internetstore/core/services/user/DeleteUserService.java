package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.user.DeleteUserResponse;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteUserService {
    @Autowired
    private UsersDatabase db;
    @Autowired
    private Validator validator;

    @Transactional
    public DeleteUserResponse execute(DeleteUserRequest request) {
        Set<ConstraintViolation<DeleteUserRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteUserResponse(errors);
        }

        return new DeleteUserResponse(db.delete(request.getUserId()));
    }
}