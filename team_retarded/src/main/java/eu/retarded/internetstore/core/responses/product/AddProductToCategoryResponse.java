package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddProductToCategoryResponse extends CoreResponse {

    private boolean productInCategory;


    public AddProductToCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductToCategoryResponse(boolean productInCategory) {
        this.productInCategory = productInCategory;
    }

    public boolean productInCategory() {
        return productInCategory;
    }

}
