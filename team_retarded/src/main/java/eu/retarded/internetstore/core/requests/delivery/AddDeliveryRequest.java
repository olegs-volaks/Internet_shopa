package eu.retarded.internetstore.core.requests.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@RequiredArgsConstructor
public class AddDeliveryRequest {

    @NotBlank(message = "Title must not be empty!")
    @Length(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private final String title;

    @NotBlank(message = "Region must not be empty!")
    @Length(min = 3, max = 100, message = "Region must be between 3 and 100 characters")
    private final String region;

    @Positive(message = "The price must be positive")
    private final double price;

}
