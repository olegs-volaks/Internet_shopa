package application.core.responses.category;

import application.core.responses.CoreError;
import application.core.responses.CoreResponse;

import java.util.List;

public class AddProductToCategoryResponse extends CoreResponse {

    private long categoryId;
    private long productId;

    public AddProductToCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductToCategoryResponse(long categoryId, long productId ) {
        this.categoryId = categoryId;
        this.productId= productId;
    }

    public long getCategoryId() {
        return categoryId;
    }
    public long getProductIdId() {
        return productId;
    }
}
