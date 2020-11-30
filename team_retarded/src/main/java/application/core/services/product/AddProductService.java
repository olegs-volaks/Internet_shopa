package application.core.services.product;

import application.core.requests.product.AddProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.AddProductResponse;
import application.core.services.validators.product.AddProductValidator;
import application.database.ProductDatabase;
import application.items.Product;

import java.util.List;

public class AddProductService {

    private final ProductDatabase db;
    private final AddProductValidator validator;

    public AddProductService(ProductDatabase db, AddProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }
        return new AddProductResponse(db.add(new Product(request.getName(), request.getDescription(), request.getPrice())));
    }
}
