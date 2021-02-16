package eu.retarded.internetstore.core.requests.category;

import eu.retarded.internetstore.core.services.validators.CategoryExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ShowAllProductsInCategoryRequest {

    @CategoryExist
    private final long categoryId;

    @NotBlank(message = "Pageable must not be empty!")
    private Pageable pageable;

}
