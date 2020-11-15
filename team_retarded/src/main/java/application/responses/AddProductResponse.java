package application.responses;

import java.util.List;

public class AddProductResponse  extends CoreResponse {


    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductResponse() {
        super();
    }
}
