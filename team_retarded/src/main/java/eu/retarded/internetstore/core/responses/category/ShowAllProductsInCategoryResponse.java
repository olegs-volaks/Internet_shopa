package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ShowAllProductsInCategoryResponse extends CoreResponse<ShowAllProductsInCategoryRequest> {

    private Page<Product> productList;

    public ShowAllProductsInCategoryResponse(Set<ConstraintViolation<ShowAllProductsInCategoryRequest>> errors,
                                             Page<Product> productList) {
        super(errors);
        this.productList = productList;
    }

    public Page <Product> getProductList() {
        return productList;
    }
}
