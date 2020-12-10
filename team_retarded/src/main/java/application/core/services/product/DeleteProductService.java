package application.core.services.product;

import application.core.requests.product.DeleteProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.DeleteProductResponse;
import application.core.services.validators.product.DeleteProductValidator;
import application.database.ProductDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.List;

@DIComponent
public class DeleteProductService {
    @DIDependency
    private ProductDatabase db;
    @DIDependency
    private DeleteProductValidator validator;

    public DeleteProductResponse execute(DeleteProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        return new DeleteProductResponse(db.delete(request.getProductIdToDelete()));
    }
}

