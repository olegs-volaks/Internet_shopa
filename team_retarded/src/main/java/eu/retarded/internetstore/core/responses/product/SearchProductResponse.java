package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class SearchProductResponse extends CoreResponse<SearchProductRequest> {

    private Page<Product> productsPage;
    private List<Product> productsList;

    public SearchProductResponse(Set<ConstraintViolation<SearchProductRequest>> errors) {
        super(errors);
    }

    public SearchProductResponse(Page<Product> productsPage, List<Product> productsList) {

        this.productsPage = productsPage;
        this.productsList = productsList;
    }

    public Page<Product> getProductsPage() {
        return productsPage;
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
