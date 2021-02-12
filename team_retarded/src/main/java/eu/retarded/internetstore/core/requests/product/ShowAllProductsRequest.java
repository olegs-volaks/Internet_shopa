package eu.retarded.internetstore.core.requests.product;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@RequiredArgsConstructor
public class ShowAllProductsRequest {

    @Positive
    private int page;
}
