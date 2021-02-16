package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class SearchProductResponse extends CoreResponse<SearchProductRequest> {

    private Page<Product> products;

    public SearchProductResponse(Set<ConstraintViolation<SearchProductRequest>> errors, Page<Product> products) {
        super(errors);
        this.products = products;
    }

    public Page<Product> getPageOfProducts() {
        return products;
    }
}
