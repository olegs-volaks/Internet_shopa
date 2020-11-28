package application.core.services.product;

import application.core.requests.product.FilterProductsByNameRequest;
import application.core.responses.CoreError;
import application.core.responses.product.FilterProductsByNameResponse;
import application.core.services.validators.product.FilterProductsByNameValidator;
import application.database.ProductDatabase;
import application.items.Product;

import java.util.List;

public class FilterProductsByNameService {
    private final ProductDatabase db;
    private final FilterProductsByNameValidator validator;

    public FilterProductsByNameService(ProductDatabase db, FilterProductsByNameValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public FilterProductsByNameResponse execute(FilterProductsByNameRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            List<Product> empty = null;
            return new FilterProductsByNameResponse(errors, empty);
        }

        return new FilterProductsByNameResponse(errors,
                db.filter(product -> product.getName().equalsIgnoreCase(request.getName1()) ||
                        product.getName().equalsIgnoreCase(request.getName2())));
    }
}

