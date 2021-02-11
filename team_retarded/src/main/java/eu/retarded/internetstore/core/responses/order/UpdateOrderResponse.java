package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateOrderResponse extends CoreResponse {

   private Long orderId;

   public Long getOrderId() {
      return orderId;
   }

   public UpdateOrderResponse(Long orderId) {
      this.orderId = orderId;
   }

   public UpdateOrderResponse(List<CoreError> errors) {
      super(errors);
   }
}

