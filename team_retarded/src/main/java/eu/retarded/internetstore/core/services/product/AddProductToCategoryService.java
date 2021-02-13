package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.product.AddProductToCategoryResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddProductToCategoryService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private Validator validator;

    @Transactional
    public AddProductToCategoryResponse execute(AddProductToCategoryRequest request) {
        Set<ConstraintViolation<AddProductToCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToCategoryResponse(errors);
        }

        return new AddProductToCategoryResponse
                (productDatabase.addProductToCategory(request.getProductId(), request.getCategoryId()));


    }
}
