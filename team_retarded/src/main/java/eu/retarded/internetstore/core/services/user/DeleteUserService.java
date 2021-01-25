package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.DeleteUserResponse;
import eu.retarded.internetstore.core.services.validators.user.DeleteUserValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteUserService {
    @Autowired
    private UsersDatabase db;
    @Autowired
    private DeleteUserValidator validator;

    public DeleteUserResponse execute(DeleteUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteUserResponse(errors);
        }

        return new DeleteUserResponse(db.delete(request.getUserId()));
    }
}