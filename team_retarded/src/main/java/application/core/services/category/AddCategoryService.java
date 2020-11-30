package application.core.services.category;

import application.core.requests.category.AddCategoryRequest;
import application.core.responses.category.AddCategoryResponse;
import application.core.services.validators.category.AddCategoryValidator;
import application.database.categories.database.CategoriesDatabase;

public class AddCategoryService {

    private final CategoriesDatabase database;
    private final AddCategoryValidator validator;

    public AddCategoryService(CategoriesDatabase database, AddCategoryValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddCategoryResponse execute(AddCategoryRequest request) {
        return null;
    }
}
