package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateDeliveryResponse extends CoreResponse {

   private Long deliveryId;

   public Long getDeliveryId() {
      return deliveryId;
   }

   public UpdateDeliveryResponse(Long deliveryId) {
      this.deliveryId = deliveryId;
   }

   public UpdateDeliveryResponse(List<CoreError> errors) {
      super(errors);
   }
}

