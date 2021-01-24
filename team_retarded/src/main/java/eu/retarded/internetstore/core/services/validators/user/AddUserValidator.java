package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserValidator {

    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddUserRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 3 || request.getName().length() > 32) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return Optional.of(new CoreError("Password", "Must not be empty!"));
        }

        if (request.getPassword().length() < 6 || request.getPassword().length() > 32) {
            return Optional.of(new CoreError("Password", "Must be between 6 and 32 characters"));
        }
        return Optional.empty();
    }


}