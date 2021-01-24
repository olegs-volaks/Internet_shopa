package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.DeleteProductResponse;
import eu.retarded.internetstore.core.services.validators.product.DeleteProductValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteProductService {
    @Autowired
    private ProductDatabase database;
    @Autowired
    private DeleteProductValidator validator;

    public DeleteProductResponse execute(DeleteProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        return new DeleteProductResponse(database.delete(request.getProductId()));
    }
}

