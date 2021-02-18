package eu.retarded.internetstore.core.requests.order;

import eu.retarded.internetstore.core.services.validators.CartExist;
import eu.retarded.internetstore.core.services.validators.DeliveryExist;
import eu.retarded.internetstore.core.services.validators.OrderExist;
import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class UpdateOrderRequest {

    @OrderExist
    private final Long id;

    @NotBlank(message = "Name must not be empty!")
    @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private final String name;

    @NotBlank(message = "Surname must not be empty!")
    @Length(min = 2, max = 100, message = "Surname must be between 2 and 100 characters")
    private final String surname;

    @NotBlank(message = "Address must not be empty!")
    @Length(min = 10, max = 1000, message = "Address must be between 10 and 1000 characters")
    private final String address;

    @CartExist
    private final long cartId;

    @DeliveryExist
    private final long deliveryId;

    @UserExist
    private final long userId;

    @PositiveOrZero(message = "The total price must be positive or zero")
    private final double totalPrice;

}
