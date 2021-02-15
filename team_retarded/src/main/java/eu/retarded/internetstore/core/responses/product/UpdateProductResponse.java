package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateProductResponse extends CoreResponse<UpdateProductRequest> {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public UpdateProductResponse(Set<ConstraintViolation<UpdateProductRequest>> errors) {
        super(errors);
    }

    public UpdateProductResponse(Product product) {
        this.product = product;
    }
}
