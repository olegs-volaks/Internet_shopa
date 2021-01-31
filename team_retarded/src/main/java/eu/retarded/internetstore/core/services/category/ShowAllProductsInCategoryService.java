package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.ShowAllProductsInCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.ShowAllProductsInCategoryValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ShowAllProductsInCategoryService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private ShowAllProductsInCategoryValidator validator;

    @Transactional
    public ShowAllProductsInCategoryResponse execute(ShowAllProductsInCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowAllProductsInCategoryResponse(null,errors);
        }
        return new ShowAllProductsInCategoryResponse(productDatabase.getList(),null);
    }
}