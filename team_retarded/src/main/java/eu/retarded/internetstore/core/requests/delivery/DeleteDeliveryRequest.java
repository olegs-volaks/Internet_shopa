package eu.retarded.internetstore.core.requests.delivery;

public class DeleteDeliveryRequest {

    private final long deleteDeliveryId;

    public DeleteDeliveryRequest(long deleteDeliveryId) {
        this.deleteDeliveryId = deleteDeliveryId;
    }

    public long getDeleteDeliveryId() {
        return deleteDeliveryId;
    }
}
