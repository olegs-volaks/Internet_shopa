package eu.retarded.internetstore.core.requests.delivery;

import eu.retarded.internetstore.core.services.validators.DeliveryExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteDeliveryRequest {

    @DeliveryExist
    private final long deleteDeliveryId;

}
