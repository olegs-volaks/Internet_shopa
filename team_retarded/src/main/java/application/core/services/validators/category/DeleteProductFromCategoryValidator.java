package application.core.services.validators.category;

import application.core.requests.category.DeleteProductFromCategoryRequest;
import application.core.responses.CoreError;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class DeleteProductFromCategoryValidator {
    @DIDependency
    private ProductDatabase productDatabase;
    @DIDependency
    private CategoriesDatabase categoriesDatabase;

    public List<CoreError> validate(DeleteProductFromCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductId(request).ifPresent(errors::add);
        validateCategoryId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductId(DeleteProductFromCategoryRequest request) {
        if (request.DeleteProductFromCategoryProductId() <= 0) {
            return Optional.of(new CoreError("ProductID", "Must not be empty or negative"));
        }
        if (productDatabase.getById(request.DeleteProductFromCategoryProductId()).isEmpty()){
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateCategoryId(DeleteProductFromCategoryRequest request) {
        if (request.DeleteProductFromCategoryCategoryID() <= 0) {
            return Optional.of(new CoreError("CategoryID", "Must not be empty or negative"));
        }
        if (categoriesDatabase.getCategory(request.DeleteProductFromCategoryCategoryID()).isEmpty()){
            return Optional.of(new CoreError("CategoryID", "Category with this ID does not exist"));
        }
        return Optional.empty();
    }
}

