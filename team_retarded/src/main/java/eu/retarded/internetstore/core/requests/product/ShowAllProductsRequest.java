package eu.retarded.internetstore.core.requests.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllProductsRequest {

    private Pageable pageable;
}
