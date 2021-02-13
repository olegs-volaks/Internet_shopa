package eu.retarded.internetstore.core.requests.category;

import eu.retarded.internetstore.core.services.validators.CategoryExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetCategoryByIdRequest {

    @CategoryExist
    private final long categoryId;

}