package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class UpdateProductRequest {

    private final long id;

    @NotBlank(message = "Name must not be empty!")
    @Length(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private final String name;

    @NotBlank(message = "Description must not be empty!")
    @Length(min = 20, max = 10000, message = "Description must be between 3 and 10000 characters")
    private final String description;

    @PositiveOrZero(message = "The price must be positive or zero")
    private final double price;

    @PositiveOrZero(message = "The count must be positive or zero")
    private final int count;

}