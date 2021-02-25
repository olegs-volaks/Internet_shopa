package eu.retarded.internetstore.core.requests.category;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class ShowAllProductsInCategoryRequest {


    private long categoryId;
    private Pageable pageable;

    public ShowAllProductsInCategoryRequest(long categoryId) {
        this.categoryId = categoryId;
    }

    public ShowAllProductsInCategoryRequest(long categoryId, Pageable pageable) {
        this.categoryId = categoryId;
        this.pageable = pageable;
    }
}
