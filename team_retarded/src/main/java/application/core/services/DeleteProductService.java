package application.core.services;

import application.bd.Database;
import application.core.requests.DeleteProductRequest;
import application.core.responses.CoreError;
import application.core.responses.DeleteProductResponse;
import application.core.services.validators.DeleteProductValidator;

import java.util.List;

public class DeleteProductService {
    private final Database db;
    private final DeleteProductValidator validator;

    public DeleteProductService(Database db, DeleteProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public DeleteProductResponse execute(DeleteProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        return new DeleteProductResponse(db.delete(request.getProductIdToDelete()));
    }
}

