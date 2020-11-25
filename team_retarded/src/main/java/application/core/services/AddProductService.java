package application.core.services;

import application.core.requests.AddProductRequest;
import application.core.responses.AddProductResponse;
import application.core.responses.CoreError;
import application.core.services.validators.AddProductValidator;
import application.database.Database;
import application.items.Product;

import java.util.List;

public class AddProductService {

    private final Database db;
    private final AddProductValidator validator;

    public AddProductService(Database db, AddProductValidator validator) {
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
