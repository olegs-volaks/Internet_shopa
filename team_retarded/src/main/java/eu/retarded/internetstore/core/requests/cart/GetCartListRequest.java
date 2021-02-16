package eu.retarded.internetstore.core.requests.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public class GetCartListRequest {


    private Pageable pageable;

}
