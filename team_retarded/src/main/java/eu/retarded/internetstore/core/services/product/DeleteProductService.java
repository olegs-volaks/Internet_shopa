package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteProductService {
    @Autowired
    private ProductDatabase database;
    @Autowired
    private Validator validator;

    @Transactional
    public DeleteProductResponse execute(DeleteProductRequest request) {
        Set<ConstraintViolation<DeleteProductRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        return new DeleteProductResponse(database.delete(request.getProductId()));
    }
}

