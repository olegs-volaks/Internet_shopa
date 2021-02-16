package eu.retarded.internetstore.core.requests.order;

import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

public class GetOrderListRequest {

    @NotBlank(message = "Pageable must not be empty!")
    private Pageable pageable;
}
