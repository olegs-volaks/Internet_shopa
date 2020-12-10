package application.core.services.category;

import application.core.requests.category.DeleteAllCategoryRequest;
import application.core.responses.category.DeleteAllCategoryResponse;
import application.database.categories.database.CategoriesDatabase;
import di.DIComponent;
import di.DIDependency;

@DIComponent
public class DeleteAllCategoryService {

    @DIDependency
    private CategoriesDatabase database;

    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        database.clear();
        return new DeleteAllCategoryResponse();
    }
}
