package application.core.services.product;

import application.core.requests.product.AddProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.AddProductResponse;
import application.core.services.validators.product.AddProductValidator;
import application.database.ProductDatabase;
import application.items.Product;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class AddProductService {

    @Autowired
    private ProductDatabase db;
    @Autowired
    private AddProductValidator validator;

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }
        return new AddProductResponse(db.add(new Product(request.getName(), request.getDescription(), request.getPrice())));
    }
}
