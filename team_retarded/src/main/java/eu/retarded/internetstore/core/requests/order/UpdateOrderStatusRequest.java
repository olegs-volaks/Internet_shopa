package eu.retarded.internetstore.core.requests.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class UpdateOrderStatusRequest {

    private final Long id;

    @NotNull(message = "Status must not be zero")
    @Size(min = 1, message = "User must have at least one role")
    private  final int status;

}
