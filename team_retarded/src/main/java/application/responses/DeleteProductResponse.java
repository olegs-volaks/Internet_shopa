package application.responses;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    public DeleteProductResponse() {
        super();
    }

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }
}
