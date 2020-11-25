package application.core.services;

import application.core.requests.SearchProductRequest;
import application.core.responses.SearchProductResponse;
import application.core.services.validators.SearchProductValidator;
import application.database.Database;

public class SearchProductService {

    private final Database db;
    private final SearchProductValidator validator;

    public SearchProductService(Database db, SearchProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public SearchProductResponse execute(SearchProductRequest request) {
        //todo: добавить логику
        return null;
    }
}
