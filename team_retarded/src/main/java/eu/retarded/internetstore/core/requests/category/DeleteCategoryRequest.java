package eu.retarded.internetstore.core.requests.category;

public class DeleteCategoryRequest {

    private final long categoryId;

    public long getCategoryId() {
        return categoryId;
    }

    public DeleteCategoryRequest(long categoryId) {
        this.categoryId = categoryId;
    }
}
