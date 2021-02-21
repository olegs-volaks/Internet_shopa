package eu.retarded.internetstore.core.requests.order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter
@RequiredArgsConstructor
public class AddOrderRequest {



    @NotBlank(message = "Name must not be empty!")
    @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private final String clientName;

    @NotBlank(message = "Surname must not be empty!")
    @Length(min = 2, max = 100, message = "Surname must be between 2 and 100 characters")
    private final String clientSurname;

    @NotBlank(message = "Address must not be empty!")
    @Length(min = 10, max = 1000, message = "Address must be between 10 and 1000 characters")
    private final String clientAddress;

    private final long deliveryId;

    private final long userId;
}
