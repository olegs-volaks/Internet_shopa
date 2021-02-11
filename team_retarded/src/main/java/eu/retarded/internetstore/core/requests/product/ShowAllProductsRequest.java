package eu.retarded.internetstore.core.requests.product;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ShowAllProductsRequest {
    private int page;
}
