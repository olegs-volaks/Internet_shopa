package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductToCategoryValidator {

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private CategoriesDatabase categoriesDatabase;

    public List<CoreError> validate(AddProductToCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductId(request).ifPresent(errors::add);
        validateCategoryId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductId(AddProductToCategoryRequest request) {
        if (request.getProductId() <= 0) {
            return Optional.of(new CoreError("ProductID", "Must not be empty or negative"));
        }
        if (productDatabase.getById(request.getProductId()).isEmpty()) {
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateCategoryId(AddProductToCategoryRequest request) {
        if (request.getCategoryId() <= 0) {
            return Optional.of(new CoreError("CategoryID", "Must not be empty or negative"));
        }
        if (categoriesDatabase.getCategory(request.getCategoryId()).isEmpty()) {
            return Optional.of(new CoreError("CategoryID", "Category with this ID does not exist"));
        }
        return Optional.empty();
    }
}
