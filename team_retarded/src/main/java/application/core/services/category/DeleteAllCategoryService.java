package application.core.services.category;

import application.core.requests.category.DeleteAllCategoryRequest;
import application.core.responses.category.DeleteAllCategoryResponse;
import application.database.categories.database.CategoriesDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class DeleteAllCategoryService {

    @Autowired
    private CategoriesDatabase database;

    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        database.clear();
        return new DeleteAllCategoryResponse();
    }
}
