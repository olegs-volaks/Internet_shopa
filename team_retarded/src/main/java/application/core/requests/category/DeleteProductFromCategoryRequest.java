package application.core.requests.category;

public class DeleteProductFromCategoryRequest {
    private final long categoryId;
    private final long productId;


    public long DeleteProductFromCategoryProductId() {
        return productId;
    }

    public long DeleteProductFromCategoryCategoryID() {
        return  categoryId;
    }

    public DeleteProductFromCategoryRequest(long categoryId, long productId) {
        this.categoryId = categoryId;
        this.productId = productId;
    }
}
