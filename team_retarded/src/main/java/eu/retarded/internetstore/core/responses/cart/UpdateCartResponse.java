package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateCartResponse extends CoreResponse<UpdateCartRequest> {

    private Long id;

    public Long getId() {
        return id;
    }

    public UpdateCartResponse(Long id) {
        this.id = id;
    }

    public UpdateCartResponse(Set<ConstraintViolation<UpdateCartRequest>> errors) {
        super(errors);
    }
}
