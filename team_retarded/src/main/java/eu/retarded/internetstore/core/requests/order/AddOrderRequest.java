package eu.retarded.internetstore.core.requests.order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddOrderRequest {

    private  String name;
    private  String surname;
    private  String address;
    private long cartId;
    private long deliveryId;
    private long userId;
    private double totalPrice;
    private int status;
}
