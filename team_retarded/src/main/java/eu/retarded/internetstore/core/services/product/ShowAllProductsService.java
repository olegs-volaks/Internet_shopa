package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
public class ShowAllProductsService {
    @Autowired
    private ProductRepository productRepository;
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
            return new ShowAllProductsResponse(errors);
        }
        List<Product> products;
        if (request.getPageable() == null) {
            products = productRepository.findAll();
            return new ShowAllProductsResponse(null, products);
        }
        Page<Product> productsPage = productRepository.findAll(request.getPageable());
        return new ShowAllProductsResponse(productsPage, productsPage.toList());
    }

}