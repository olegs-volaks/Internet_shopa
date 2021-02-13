package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class SearchProductResponse extends CoreResponse<SearchProductRequest> {

    private List<Product> products;

    public SearchProductResponse(Set<ConstraintViolation<SearchProductRequest>> errors, List<Product> products) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
