package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateCartResponse extends CoreResponse {

    private Long id;

    public Long getId() {
        return id;
    }

    public UpdateCartResponse(Long id) {this.id = id;}

    public UpdateCartResponse(List<CoreError> errors) {
        super(errors);
    }
}
