package eu.retarded.internetstore.core.requests.product;

import eu.retarded.internetstore.core.services.validators.ProductExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteProductFromCategoryRequest {

    @ProductExist
    private final long productId;

}
