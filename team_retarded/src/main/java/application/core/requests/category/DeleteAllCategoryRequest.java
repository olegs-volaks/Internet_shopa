package application.core.requests.category;

public class DeleteAllCategoryRequest {

    private final long categoryId;

    public long getCategoryId() {
        return categoryId;
    }

    public DeleteAllCategoryRequest(long categoryId) {
        this.categoryId = categoryId;
    }
}
