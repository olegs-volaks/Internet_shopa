package application.core.services.validators.category;

import application.core.requests.category.DeleteCategoryRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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