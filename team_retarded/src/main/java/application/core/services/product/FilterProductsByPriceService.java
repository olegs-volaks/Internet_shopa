package application.core.services.product;

import application.core.requests.product.FilterProductsByPriceRequest;
import application.core.responses.CoreError;
import application.core.responses.product.FilterProductsByPriceResponse;
import application.core.services.validators.product.FilterProductsByPriceValidator;
import application.database.ProductDatabase;
import application.items.Product;

import java.math.BigDecimal;
import java.util.List;

public class FilterProductsByPriceService {
    private final ProductDatabase db;
    private final FilterProductsByPriceValidator validator;

    public FilterProductsByPriceService(ProductDatabase db, FilterProductsByPriceValidator validator) {
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

