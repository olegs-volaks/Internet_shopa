package eu.retarded.internetstore.core.requests.category;

public class GetCategoryByIdRequest {
    private Long categoryId;

    public GetCategoryByIdRequest(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}