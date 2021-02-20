package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteDeliveryResponse extends CoreResponse<DeleteDeliveryRequest> {

    private boolean isDeleted;

    public DeleteDeliveryResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public DeleteDeliveryResponse(Set<ConstraintViolation<DeleteDeliveryRequest>> errors) {
        super(errors);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
