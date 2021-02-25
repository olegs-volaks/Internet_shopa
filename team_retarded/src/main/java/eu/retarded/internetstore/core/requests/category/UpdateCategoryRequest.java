package eu.retarded.internetstore.core.requests.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class UpdateCategoryRequest {

    private final long id;

    @NotBlank(message = "Name must not be empty!")
    @Length(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private final String name;
}
