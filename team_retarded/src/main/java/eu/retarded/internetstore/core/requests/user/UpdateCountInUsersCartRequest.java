package eu.retarded.internetstore.core.requests.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class UpdateCountInUsersCartRequest {

    private final long userId;

    private final long productId;

    @PositiveOrZero(message = "Must be positive or 0")
    private final int count;
}
