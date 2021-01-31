package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShowAllProductsInCategoryValidator {
    @Autowired
    private CategoriesDatabase categoriesDatabase;

    public List<CoreError> validate(ShowAllProductsInCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateId(ShowAllProductsInCategoryRequest request) {
        if (request.getCategoryId() <= 0) {
            return Optional.of(new CoreError("ID", "Must be more than 0"));
        }
        if (!categoriesDatabase.isExist(request.getCategoryId())) {
            return Optional.of(new CoreError("CategoryID", "Category with this ID does not exist"));
        }
        return Optional.empty();
    }
}
