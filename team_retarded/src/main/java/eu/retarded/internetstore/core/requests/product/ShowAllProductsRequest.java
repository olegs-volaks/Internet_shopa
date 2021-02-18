package eu.retarded.internetstore.core.requests.product;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public class ShowAllProductsRequest {

    private final Pageable pageable;
}
