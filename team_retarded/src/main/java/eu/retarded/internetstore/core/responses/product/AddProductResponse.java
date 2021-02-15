package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
public class AddProductResponse extends CoreResponse<AddProductRequest> {

    private Product product;

    public AddProductResponse(Product product) {
        this.product = product;
    }

    public AddProductResponse(Set<ConstraintViolation<AddProductRequest>> errors) {
        super(errors);
    }
}
