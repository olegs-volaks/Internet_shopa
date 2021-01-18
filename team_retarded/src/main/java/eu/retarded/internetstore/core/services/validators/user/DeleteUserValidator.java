package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteUserValidator {

    public List<CoreError> validate(DeleteUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteUserRequest request) {
        if (request.getUserIdToDelete() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty, negative or fractional"));
        }

        return Optional.empty();
    }

}
