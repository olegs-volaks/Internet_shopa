package application.core.services.product;

import application.core.requests.product.AddProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.AddProductResponse;
import application.core.services.validators.product.AddProductValidator;
import application.database.ProductDatabase;
import application.items.Product;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.List;

@DIComponent
public class AddProductService {

    @DIDependency
    private ProductDatabase db;
    @DIDependency
    private AddProductValidator validator;

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }
        return new AddProductResponse(db.add(new Product(request.getName(), request.getDescription(), request.getPrice())));
    }
}
