package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class UpdateProductRequest {
    private final long id;
    private final String name;
    private final String description;
    private final BigDecimal price;

}