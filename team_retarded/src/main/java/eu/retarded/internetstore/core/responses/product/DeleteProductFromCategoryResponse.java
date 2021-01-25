package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteProductFromCategoryResponse extends CoreResponse {
    private boolean productNotInCategory;


    public DeleteProductFromCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductFromCategoryResponse(boolean productNotInCategory) {
        this.productNotInCategory = productNotInCategory;
    }

    public boolean productNotInCategory() {
        return productNotInCategory;
    }


}

