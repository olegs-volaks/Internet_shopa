package eu.retarded.internetstore.core.requests.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetOrderListPagingRequest {
    private final int  page;
}
