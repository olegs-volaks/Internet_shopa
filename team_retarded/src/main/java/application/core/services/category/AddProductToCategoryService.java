package application.core.services.category;

import application.core.requests.category.AddProductToCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.AddProductToCategoryResponse;
import application.core.services.validators.category.AddProductToCategoryValidator;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;

import java.util.List;


public class AddProductToCategoryService {
    private final CategoriesDatabase categoriesDatabase;
    private final ProductDatabase productDatabase;
    private final AddProductToCategoryValidator validator;

    public AddProductToCategoryService(CategoriesDatabase categoriesDatabase,ProductDatabase productDatabase,
                                       AddProductToCategoryValidator validator) {
        this.categoriesDatabase = categoriesDatabase;
        this.productDatabase = productDatabase;
        this.validator = validator;
    }

    public AddProductToCategoryResponse execute(AddProductToCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToCategoryResponse(errors);
        }
        if (categoriesDatabase.getCategory(request.AddProductToCategoryCategoryID()).isPresent()&&
                productDatabase.getById(request.AddProductToCategoryProductID()).isPresent()){
        categoriesDatabase.getCategory(request.AddProductToCategoryCategoryID()).get().
                add((productDatabase.getById(request.AddProductToCategoryProductID())).get());
        }
        return new AddProductToCategoryResponse(request.AddProductToCategoryCategoryID(), request.AddProductToCategoryProductID());
    }
}
