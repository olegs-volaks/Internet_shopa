package application.core.services.category;

import application.core.requests.category.DeleteAllCategoryRequest;
import application.core.responses.category.DeleteAllCategoryResponse;
import application.database.categories.database.CategoriesDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;


@DIComponent
public class DeleteAllCategoryService {

    @DIDependency
    private CategoriesDatabase database;

    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        database.clear();
        return new DeleteAllCategoryResponse();
    }
}
