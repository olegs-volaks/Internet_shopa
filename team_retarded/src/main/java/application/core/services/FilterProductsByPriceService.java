package application.core.services;

import application.core.requests.FilterProductsByPriceRequest;
import application.core.responses.CoreError;
import application.core.responses.FilterProductsByPriceResponse;
import application.core.services.validators.FilterProductsByPriceValidator;
import application.database.Database;
import application.items.Product;

import java.math.BigDecimal;
import java.util.List;

public class FilterProductsByPriceService {
    private final Database db;
    private final FilterProductsByPriceValidator validator;

    public FilterProductsByPriceService(Database db, FilterProductsByPriceValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public FilterProductsByPriceResponse execute(FilterProductsByPriceRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            List<Product> empty = null;
            return new FilterProductsByPriceResponse(errors, empty);
        }

        return new FilterProductsByPriceResponse(errors,
                db.filter(product -> product.getPrice().compareTo(new BigDecimal(Double.toString(request.getPriceMin()))) >= 0 &&
                        product.getPrice().compareTo(new BigDecimal(Double.toString(request.getPriceMax()))) <= 0));
    }
}

