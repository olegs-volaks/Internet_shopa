package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetUserByIdValidator {

    @Autowired
    private UsersDatabase usersDatabase;

    public List<CoreError> validate(GetUserByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetUserByIdRequest request) {
        long id = request.getUserId();
        if (id <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty, negative or fractional"));
        }
        if (!usersDatabase.isExist(id)) {
            return Optional.of(new CoreError("ID", "The user with the given id does not exist"));
        }
        return Optional.empty();
    }
}
