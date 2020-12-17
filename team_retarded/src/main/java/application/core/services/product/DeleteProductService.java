package application.core.services.product;

import application.core.requests.product.DeleteProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.DeleteProductResponse;
import application.core.services.validators.product.DeleteProductValidator;
import application.database.ProductDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteProductService {
    @Autowired private ProductDatabase database;
    @Autowired private DeleteProductValidator validator;

    public DeleteProductResponse execute(DeleteProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        return new DeleteProductResponse(database.delete(request.getProductIdToDelete()));
    }
}

