package application.core.services.validators.category;

import application.core.requests.category.AddProductToCategoryRequest;
import application.core.responses.CoreError;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class AddProductToCategoryValidator {

    @DIDependency
    private ProductDatabase productDatabase;
    @DIDependency
    private CategoriesDatabase categoriesDatabase;

    public List<CoreError> validate(AddProductToCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductId(request).ifPresent(errors::add);
        validateCategoryId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductId(AddProductToCategoryRequest request) {
        if (request.AddProductToCategoryProductID() <= 0) {
            return Optional.of(new CoreError("ProductID", "Must not be empty or negative"));
        }
        if (productDatabase.getById(request.AddProductToCategoryProductID()).isEmpty()){
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateCategoryId(AddProductToCategoryRequest request) {
        if (request.AddProductToCategoryCategoryID() <= 0) {
            return Optional.of(new CoreError("CategoryID", "Must not be empty or negative"));
        }
        if (categoriesDatabase.getCategory(request.AddProductToCategoryCategoryID()).isEmpty()){
            return Optional.of(new CoreError("CategoryID", "Category with this ID does not exist"));
        }
        return Optional.empty();
    }
}
