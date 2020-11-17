package application.core.services;

import application.bd.Database;
import application.core.requests.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.GetProductByIdResponse;
import application.core.services.validators.GetProductByIdValidator;

import java.util.List;

public class GetProductByIdService {

    private final Database db;
    private final GetProductByIdValidator validator;

    public GetProductByIdService(Database db,GetProductByIdValidator validator) {
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
