package application.core.services.validators.category;

import application.core.requests.category.DeleteAllCategoryRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteAllCategoryValidator {

    public List<CoreError> validate(DeleteAllCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateId(DeleteAllCategoryRequest request) {
        if (request.getCategoryId() <= 0) {
            return Optional.of(new CoreError("ID","Must not be empty"));
        }
        return Optional.empty();

    }
}
