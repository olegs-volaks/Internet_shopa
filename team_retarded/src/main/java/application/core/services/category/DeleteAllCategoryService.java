package application.core.services.category;

import application.core.requests.category.DeleteAllCategoryRequest;
import application.core.responses.category.DeleteAllCategoryResponse;

import application.database.categories.database.CategoriesDatabase;



public class DeleteAllCategoryService {

    private final CategoriesDatabase database;

    public DeleteAllCategoryService(CategoriesDatabase database) {
        this.database = database;

    }
    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        database.clear();
        return new DeleteAllCategoryResponse();
    }
}
