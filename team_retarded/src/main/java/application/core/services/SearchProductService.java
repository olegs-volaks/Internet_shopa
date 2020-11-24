package application.core.services;

import application.bd.Database;
import application.core.requests.SearchProductRequest;
import application.core.responses.CoreError;
import application.core.responses.SearchProductResponse;
import application.core.services.validators.SearchProductValidator;
import application.items.Product;

import java.util.List;

public class SearchProductService {

    private final Database db;
    private final SearchProductValidator validator;

    public SearchProductService(Database db, SearchProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public SearchProductResponse execute(SearchProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            List<Product> empty = null;
            return new SearchProductResponse(errors, null);
        }
        return new SearchProductResponse(errors,null);
    }
}
