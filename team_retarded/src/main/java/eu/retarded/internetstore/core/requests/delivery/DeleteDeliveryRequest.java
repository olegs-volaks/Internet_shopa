package eu.retarded.internetstore.core.requests.delivery;

public class DeleteDeliveryRequest {

    private final long deliveryIdToDelete;

    public DeleteDeliveryRequest(long deliveryIdToDelete) {
        this.deliveryIdToDelete = deliveryIdToDelete;
    }

    public long getDeliveryIdToDelete() {
        return deliveryIdToDelete;
    }
}
