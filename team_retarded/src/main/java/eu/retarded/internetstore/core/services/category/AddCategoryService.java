package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.AddCategoryValidator;
import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddCategoryService {

    @Autowired
    private CategoriesDatabase database;
    @Autowired
    private AddCategoryValidator validator;

    public AddCategoryResponse execute(AddCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCategoryResponse(errors);
        }
        return new AddCategoryResponse(database.addCategory(new ProductListCategory(request.getName())));
    }
}
