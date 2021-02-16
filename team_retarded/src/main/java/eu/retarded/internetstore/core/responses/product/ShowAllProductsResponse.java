package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ShowAllProductsResponse extends CoreResponse<ShowAllProductsRequest> {

    private Page<Product> products;

    public ShowAllProductsResponse(Set<ConstraintViolation<ShowAllProductsRequest>> errors, Page <Product> products) {
        super(errors);
        this.products = products;
    }

    public Page<Product> getProducts() {
        return products;
    }
}

