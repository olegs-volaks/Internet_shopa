package eu.retarded.internetstore.core.requests.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCartListRequest {


    private Pageable pageable;

}
