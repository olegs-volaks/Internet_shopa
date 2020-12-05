package application.core.services.product;

import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.validators.product.GetProductByIdValidator;
import application.database.ProductDatabase;

import java.util.List;

public class GetProductByIdService {

    private final ProductDatabase db;
    private final GetProductByIdValidator validator;

    public GetProductByIdService(ProductDatabase db, GetProductByIdValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public GetProductByIdResponse execute(GetProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetProductByIdResponse(errors);
        }
        return new GetProductByIdResponse(db.getById(request.getProductId()));
    }
}
