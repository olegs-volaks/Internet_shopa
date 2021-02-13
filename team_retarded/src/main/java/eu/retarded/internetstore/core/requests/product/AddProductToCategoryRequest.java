package eu.retarded.internetstore.core.requests.product;

import eu.retarded.internetstore.core.services.validators.CategoryExist;
import eu.retarded.internetstore.core.services.validators.ProductExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class AddProductToCategoryRequest {

    @NotBlank(message = "The category ID must not be empty!")
    @CategoryExist
    private final long categoryId;

    @NotBlank(message = "The product ID must not be empty!")
    @ProductExist
    private final long productId;

}

