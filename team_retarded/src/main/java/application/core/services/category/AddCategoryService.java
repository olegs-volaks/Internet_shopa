package application.core.services.category;

import application.core.requests.category.AddCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.AddCategoryResponse;
import application.core.services.validators.category.AddCategoryValidator;
import application.database.categories.category.ProductListCategory;
import application.database.categories.database.CategoriesDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.List;

@DIComponent
public class AddCategoryService {

    @DIDependency
    private CategoriesDatabase database;
    @DIDependency
    private AddCategoryValidator validator;

    public AddCategoryResponse execute(AddCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCategoryResponse(errors);
        }
        return new AddCategoryResponse(database.addCategory(new ProductListCategory(request.getName())));
    }
}
