package eu.retarded.internetstore.core.requests.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class UpdateDeliveryRequest {

    private final long Id;
    private final String title;
    private final String region;
    private final double price;

}
