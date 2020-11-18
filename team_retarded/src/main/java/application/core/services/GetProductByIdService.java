package application.core.services;

import application.bd.Database;
import application.core.requests.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.GetProductByIdResponse;
import application.core.services.validators.GetProductByIdValidator;
import application.items.Product;

import java.util.List;
import java.util.Optional;

public class GetProductByIdService {

    private final Database db;
    private final GetProductByIdValidator validator;

    public GetProductByIdService(Database db,GetProductByIdValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public GetProductByIdResponse execute(GetProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        Optional<Product> product = db.getById(request.getProductId());
        if (product.isEmpty()) {
            errors.add(new CoreError("ID", "No product found with this ID"));
            return new GetProductByIdResponse(errors);
        } else {
            return new GetProductByIdResponse(product.get());
        }
    }
}
