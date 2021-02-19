package eu.retarded.internetstore.core.requests.category;

import eu.retarded.internetstore.core.services.validators.CategoryExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public class ShowAllProductsInCategoryRequest {

    @CategoryExist
    private final long categoryId;


    private final Pageable pageable;

}
