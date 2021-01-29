package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class GetByIdDeliveryResponse  extends CoreResponse {

    private Optional<Delivery> id;

    public GetByIdDeliveryResponse(Optional<Delivery> id) {
        this.id = id;
    }

    public GetByIdDeliveryResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Delivery> getId() {
        return id;
    }
}
