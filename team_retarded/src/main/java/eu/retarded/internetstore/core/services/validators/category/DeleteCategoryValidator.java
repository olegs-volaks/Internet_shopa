package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteCategoryValidator {

    public List<CoreError> validate(DeleteCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteCategoryRequest request) {
        if (request.getCategoryId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        return Optional.empty();
    }
}
