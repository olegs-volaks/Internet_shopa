package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteAllCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteAllCategoryResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DeleteAllCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public DeleteAllCategoryResponse execute(DeleteAllCategoryRequest request) {
        categoryRepository.deleteAll();
        return new DeleteAllCategoryResponse();
    }
}
