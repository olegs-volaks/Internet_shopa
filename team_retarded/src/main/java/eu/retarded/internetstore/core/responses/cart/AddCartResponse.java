package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddCartResponse extends CoreResponse<AddCartRequest> {

    private Long id;

    public AddCartResponse(Long id) {
        this.id = id;
    }

    public AddCartResponse(Set<ConstraintViolation<AddCartRequest>> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
