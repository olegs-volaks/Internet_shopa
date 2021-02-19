package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Map;
import java.util.Set;

public class GetProductInCartResponse extends CoreResponse<GetProductInCartRequest> {

    private Map<Product, Integer> products;

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public GetProductInCartResponse(Map<Product, Integer> products) {
        this.products = products;
    }

    public GetProductInCartResponse(Set<ConstraintViolation<GetProductInCartRequest>> errors) {
        super(errors);
    }
}
