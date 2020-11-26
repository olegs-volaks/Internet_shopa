package application.core.services.product;

import application.core.requests.product.DeleteProductRequest;
import application.core.responses.product.CoreError;
import application.core.responses.product.DeleteProductResponse;
import application.core.services.validators.product.DeleteProductValidator;
import application.database.ProductDatabase;

import java.util.List;

public class DeleteProductService {
    private final ProductDatabase db;
    private final DeleteProductValidator validator;

    public DeleteProductService(ProductDatabase db, DeleteProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public DeleteProductResponse execute(DeleteProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }
        db.delete(request.getProductIdToDelete());
        if (db.getById(request.getProductIdToDelete()).isEmpty()) {
            return new DeleteProductResponse(true);
        }
        return new DeleteProductResponse(false);
    }
}

