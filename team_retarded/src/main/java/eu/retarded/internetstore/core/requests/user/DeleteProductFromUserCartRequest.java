package eu.retarded.internetstore.core.requests.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteProductFromUserCartRequest {

    private final long userId;

    private final long productId;

}
