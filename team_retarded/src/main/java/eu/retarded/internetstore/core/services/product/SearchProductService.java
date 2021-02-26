package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.product.SearchProductResponse;
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
public class SearchProductService {

    @Autowired
    private ProductRepository productRepository;
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
            return new SearchProductResponse(errors);
        }
        List<Product> products;
        if (request.getPageable() == null) {
            products = productRepository.findByNameContaining(request.getKeyWord());
            return new SearchProductResponse(null, products);
        }
        Page<Product> productsPage = productRepository.findByNameContaining(request.getKeyWord(), request.getPageable());
        return new SearchProductResponse(productsPage, productsPage.toList());
    }
}
