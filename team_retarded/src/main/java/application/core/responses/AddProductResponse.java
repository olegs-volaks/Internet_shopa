package application.core.responses;

import java.util.List;

public class AddProductResponse extends CoreResponse {

    public AddProductResponse() {
        super();
    }

    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }
}
