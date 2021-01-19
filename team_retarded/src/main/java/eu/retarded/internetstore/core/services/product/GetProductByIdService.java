package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.GetProductByIdResponse;
import eu.retarded.internetstore.core.services.validators.product.GetProductByIdValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
