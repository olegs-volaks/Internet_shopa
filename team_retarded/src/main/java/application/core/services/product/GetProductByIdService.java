package application.core.services.product;

import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.validators.product.GetProductByIdValidator;
import application.database.ProductDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.List;

@DIComponent
public class GetProductByIdService {

    @DIDependency
    private ProductDatabase db;
    @DIDependency
    private GetProductByIdValidator validator;

    public GetProductByIdResponse execute(GetProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetProductByIdResponse(errors);
        }
        return new GetProductByIdResponse(db.getById(request.getProductId()));
    }
}
