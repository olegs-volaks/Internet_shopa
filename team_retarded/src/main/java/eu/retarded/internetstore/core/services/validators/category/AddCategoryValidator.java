package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddCategoryValidator {

    public List<CoreError> validate(AddCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(AddCategoryRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 4 || request.getName().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 4 and 100 characters"));
        }
        return Optional.empty();
    }
}
