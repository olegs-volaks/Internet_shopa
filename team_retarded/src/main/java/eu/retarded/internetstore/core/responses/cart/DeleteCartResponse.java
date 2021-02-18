package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteCartResponse extends CoreResponse<DeleteCartRequest> {

    private boolean isDelete;

    public DeleteCartResponse(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public DeleteCartResponse(Set<ConstraintViolation<DeleteCartRequest>> errors) {
        super(errors);
    }


    public boolean isDelete() {
        return isDelete;
    }
}
