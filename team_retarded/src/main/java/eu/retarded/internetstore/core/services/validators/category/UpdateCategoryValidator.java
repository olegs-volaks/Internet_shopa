package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateCategoryValidator {

    @Autowired
    private CategoriesDatabase categoriesDatabase;

    public List<CoreError> validate(UpdateCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(UpdateCategoryRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!categoriesDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID", "The category with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateName(UpdateCategoryRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 3 || request.getName().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }
}
