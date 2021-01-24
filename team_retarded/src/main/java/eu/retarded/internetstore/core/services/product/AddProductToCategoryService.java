package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.AddProductToCategoryResponse;
import eu.retarded.internetstore.core.services.validators.product.AddProductToCategoryValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductToCategoryService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private AddProductToCategoryValidator validator;


    public AddProductToCategoryResponse execute(AddProductToCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToCategoryResponse(errors);
        }

        return new AddProductToCategoryResponse
                (productDatabase.addProductToCategory(request.getProductId(), request.getCategoryId()));


    }
}
