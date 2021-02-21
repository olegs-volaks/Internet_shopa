package eu.retarded.internetstore.core.requests.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteDeliveryRequest {

    private final long deleteDeliveryId;

}
