package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateUserValidator {

    @Autowired
    private UsersDatabase usersDatabase;

    public List<CoreError> validate(UpdateUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateRole(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateEmail(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(UpdateUserRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!usersDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID", "The user with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRole(UpdateUserRequest request) {
        if (request.getRole() < 0 && request.getRole() > 4) {
            return Optional.of(new CoreError("Role", "Must be between 1 and 3 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateName(UpdateUserRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 3 || request.getName().length() > 32) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateSurname(UpdateUserRequest request) {
        if (request.getSurname() == null || request.getSurname().isEmpty()) {
            return Optional.of(new CoreError("Surname", "Must not be empty!"));
        }

        if (request.getSurname().length() < 3 || request.getSurname().length() > 32) {
            return Optional.of(new CoreError("Surname", "Must be between 3 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateEmail(UpdateUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return Optional.of(new CoreError("Email", "Must not be empty!"));
        }

        if (request.getEmail().length() < 6 || request.getEmail().length() > 32) {
            return Optional.of(new CoreError("Email", "Must be between 8 and 32 characters"));
        }

        if (!request.getEmail().contains("@")) {
            return Optional.of(new CoreError("Email", "Must contain \"@\""));
        }
        return Optional.empty();
    }
}
