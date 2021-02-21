package eu.retarded.internetstore.core.requests.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetByIdCartRequest {


    private final Long id;
}
