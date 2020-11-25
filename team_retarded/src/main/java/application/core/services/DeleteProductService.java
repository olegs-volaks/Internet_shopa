package application.core.services;

import application.core.requests.DeleteProductRequest;
import application.core.responses.CoreError;
import application.core.responses.DeleteProductResponse;
import application.core.services.validators.DeleteProductValidator;
import application.database.Database;

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
        db.delete(request.getProductIdToDelete());
        if (db.getById(request.getProductIdToDelete()).isEmpty()) {
            return new DeleteProductResponse(true);
        }
        return new DeleteProductResponse(false);
    }
}

