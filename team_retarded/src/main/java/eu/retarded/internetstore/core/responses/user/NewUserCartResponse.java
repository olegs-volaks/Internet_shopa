package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.user.NewUserCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

public class NewUserCartResponse extends CoreResponse<NewUserCartRequest> {

    private Cart oldCart;

    public Cart getOldCart() {
        return oldCart;
    }

    public NewUserCartResponse(Cart oldCart) {
        this.oldCart = oldCart;
    }
}
