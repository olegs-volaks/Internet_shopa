package application.core.services;

import application.bd.Database;
import application.core.requests.SearchProductRequest;
import application.core.responses.CoreError;
import application.core.responses.SearchProductResponse;
import application.core.services.validators.SearchProductValidator;

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
            return new SearchProductResponse(errors, null);
        }

        return new SearchProductResponse(errors,
                db.filter(product -> (product.getName().toLowerCase().contains(request.getName().toLowerCase()))||
                        (product.getDescription().toLowerCase().contains(request.getDescription().toLowerCase()))));
    }
}
