package application.core.requests.category;

public class AddProductToCategoryRequest {

    private final long categoryId;
    private final long productId;


    public long AddProductToCategoryProductID() {
        return productId;
    }

    public long AddProductToCategoryCategoryID() {
        return  categoryId;
    }

    public AddProductToCategoryRequest(long categoryId, long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }
}

