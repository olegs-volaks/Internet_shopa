package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChangeUserPasswordValidator {

    @Autowired
    private UsersDatabase usersDatabase;

    public List<CoreError> validate(ChangeUserPasswordRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(ChangeUserPasswordRequest request) {
        if (request.getUserId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty, negative or fractional"));
        }
        if (!usersDatabase.isExist(request.getUserId())) {
            return Optional.of(new CoreError("ID", "The user with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePassword(ChangeUserPasswordRequest request) {
        if (request.getNewPassword() == null || request.getNewPassword().isEmpty()) {
            return Optional.of(new CoreError("New password", "Must not be empty!"));
        }

        if (request.getNewPassword().length() < 6 || request.getNewPassword().length() > 32) {
            return Optional.of(new CoreError("New password", "Must be between 6 and 32 characters"));
        }
        return Optional.empty();
    }
}
