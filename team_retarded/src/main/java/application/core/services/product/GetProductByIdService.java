package application.core.services.product;

import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.product.CoreError;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.validators.product.GetProductByIdValidator;
import application.database.ProductDatabase;
import application.items.Product;

import java.util.List;
import java.util.Optional;

public class GetProductByIdService {

    private final ProductDatabase db;
    private final GetProductByIdValidator validator;

    public GetProductByIdService(ProductDatabase db, GetProductByIdValidator validator) {
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
