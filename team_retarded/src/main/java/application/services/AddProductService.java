package application.services;

import application.bd.Database;
import application.items.Product;
import application.requests.AddProductRequests;
import application.responses.AddProductResponse;
import application.responses.CoreError;

import java.util.List;

public class AddProductService {

    private final Database db;
    private final AddProductValidator validator;


    public AddProductService(Database db,
                             AddProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public void addProduct(String name, String description, double price) {
        db.add(name, description, price);
    }

    public AddProductResponse execute(AddProductRequests requests) {
        List<CoreError> errors = validator.validate(requests);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }
        db.add(requests.getName(), requests.getDescription(), requests.getPrice());
//        Product product = new Product(requests.getId(),requests.getName(), requests.getDescription(), requests.getPrice());
        return new AddProductResponse();
    }
}
