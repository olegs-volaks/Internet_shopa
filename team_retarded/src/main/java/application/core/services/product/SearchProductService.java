package application.core.services.product;

import application.core.requests.product.SearchProductRequest;
import application.core.responses.product.SearchProductResponse;
import application.core.services.validators.product.SearchProductValidator;
import application.database.ProductDatabase;

public class SearchProductService {

    private final ProductDatabase db;
    private final SearchProductValidator validator;

    public SearchProductService(ProductDatabase db, SearchProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public SearchProductResponse execute(SearchProductRequest request) {
        //todo: добавить логику
        return null;
    }
}
