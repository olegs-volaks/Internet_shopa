package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetProductByIdRequest {

    private final long productId;

}
