package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateDeliveryResponse extends CoreResponse {

   private long deliveryId;

   public long getDeliveryId() {
      return deliveryId;
   }

   public UpdateDeliveryResponse(long deliveryId) {
      this.deliveryId = deliveryId;
   }

   public UpdateDeliveryResponse(List<CoreError> errors) {
      super(errors);
   }
}

