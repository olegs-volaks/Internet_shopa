package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteCartResponse extends CoreResponse {

    private boolean isCartDeleted;

    public DeleteCartResponse(boolean isCartDeleted) {
        this.isCartDeleted = isCartDeleted;
    }

    public DeleteCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isCartDeleted() {
        return isCartDeleted;
    }
}
