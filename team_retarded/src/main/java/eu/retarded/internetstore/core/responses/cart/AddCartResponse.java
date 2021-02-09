package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddCartResponse  extends CoreResponse {

    private Long id;

    public AddCartResponse(Long id) {
        this.id = id;
    }

    public AddCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
