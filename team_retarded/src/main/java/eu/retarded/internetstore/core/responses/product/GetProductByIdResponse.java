package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetProductByIdResponse extends CoreResponse<GetProductByIdRequest> {

    private Product product;

    public GetProductByIdResponse(Product product) {
        this.product = product;
    }

    public GetProductByIdResponse(Set<ConstraintViolation<GetProductByIdRequest>> errors) {
        super(errors);
    }

    public Product getProduct() {
        return product;
    }
}
