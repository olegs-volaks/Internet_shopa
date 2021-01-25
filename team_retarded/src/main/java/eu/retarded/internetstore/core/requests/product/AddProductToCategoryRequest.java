package eu.retarded.internetstore.core.requests.product;

public class AddProductToCategoryRequest {

    private final long categoryId;
    private final long productId;


    public long getProductId() {
        return productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public AddProductToCategoryRequest(long categoryId, long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }
}

