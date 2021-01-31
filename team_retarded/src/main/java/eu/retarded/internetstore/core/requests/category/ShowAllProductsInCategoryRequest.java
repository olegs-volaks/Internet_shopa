package eu.retarded.internetstore.core.requests.category;

public class ShowAllProductsInCategoryRequest {

    private final Long categoryId;

    public ShowAllProductsInCategoryRequest(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
