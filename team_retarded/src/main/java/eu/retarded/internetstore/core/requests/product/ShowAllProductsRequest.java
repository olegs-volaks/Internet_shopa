package eu.retarded.internetstore.core.requests.product;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class ShowAllProductsRequest {

    @PositiveOrZero(message = "The page must be positive or zero")
    private int page;
}
