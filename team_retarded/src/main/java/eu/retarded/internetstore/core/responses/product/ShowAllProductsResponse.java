package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class ShowAllProductsResponse extends CoreResponse<ShowAllProductsRequest> {

    private Page<Product> productsPage;
    private List<Product> productsList;

    public ShowAllProductsResponse(Set<ConstraintViolation<ShowAllProductsRequest>> errors, Page <Product> products) {
        super(errors);
        this.productsPage = products;
    }

    public ShowAllProductsResponse(Set<ConstraintViolation<ShowAllProductsRequest>> errors, List <Product> products) {
        super(errors);
        this.productsList = products;
    }

    public Page<Product> getProductsPage() {
        return productsPage;
    }
    public List<Product> getProductsList() {
        return productsList;
    }
}

