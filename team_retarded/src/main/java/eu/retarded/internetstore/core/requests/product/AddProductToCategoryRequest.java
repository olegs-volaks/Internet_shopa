package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddProductToCategoryRequest {

    private final long categoryId;

    private final long productId;

}

