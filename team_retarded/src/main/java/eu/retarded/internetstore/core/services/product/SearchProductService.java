package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.product.SearchProductResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
public class SearchProductService {

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private Validator validator;

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Transactional
    public SearchProductResponse execute(SearchProductRequest request) {
        Set<ConstraintViolation<SearchProductRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchProductResponse(errors, null);
        }

        List<Product> products = productDatabase.search(request.getKeyWord(), request.getSorting(), request.getPage());
        return new SearchProductResponse(null, products);
    }
}
