package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
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
public class ShowAllProductsService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private Validator validator;

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Transactional
    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        Set<ConstraintViolation<ShowAllProductsRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowAllProductsResponse(errors, null);
        }

        List<Product> products = productDatabase.getList();


        return new ShowAllProductsResponse(null, products);
    }

}