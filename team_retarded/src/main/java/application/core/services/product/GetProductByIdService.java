package application.core.services.product;

import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.validators.product.GetProductByIdValidator;
import application.database.ProductDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class GetProductByIdService {

    @Autowired
    private ProductDatabase db;
    @Autowired
    private GetProductByIdValidator validator;

    public GetProductByIdResponse execute(GetProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetProductByIdResponse(errors);
        }
        return new GetProductByIdResponse(db.getById(request.getProductId()));
    }
}
