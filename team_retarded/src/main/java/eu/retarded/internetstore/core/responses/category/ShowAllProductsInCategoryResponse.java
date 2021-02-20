package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class ShowAllProductsInCategoryResponse extends CoreResponse<ShowAllProductsInCategoryRequest> {

    private Page<Product> productPage;
    private List<Product> productList;

    public ShowAllProductsInCategoryResponse(Set<ConstraintViolation<ShowAllProductsInCategoryRequest>> errors) {
        super(errors);
    }

    public ShowAllProductsInCategoryResponse(Page<Product> productPage, List<Product> productList) {
        this.productPage = productPage;
        this.productList = productList;
    }

    public Page<Product> getProductPage() {
        return productPage;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
