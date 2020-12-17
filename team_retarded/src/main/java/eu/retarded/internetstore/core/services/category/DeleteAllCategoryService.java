package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteAllCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteAllCategoryResponse;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeleteAllCategoryService {

    @Autowired
    private CategoriesDatabase database;

    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        database.clear();
        return new DeleteAllCategoryResponse();
    }
}
