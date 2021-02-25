package eu.retarded.internetstore.core.requests.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateOrderStatusRequest {

    private final Long id;

    private  final int status;

}
