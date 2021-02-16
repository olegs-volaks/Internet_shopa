package eu.retarded.internetstore.core.requests.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class GetOrderListPagingRequest {

    @NotBlank(message = "Pageable must not be empty!")
    private Pageable pageable;
}
