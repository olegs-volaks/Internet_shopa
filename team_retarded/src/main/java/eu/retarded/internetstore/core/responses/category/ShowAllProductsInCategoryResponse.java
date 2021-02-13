package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class ShowAllProductsInCategoryResponse extends CoreResponse<ShowAllProductsInCategoryRequest> {

    private List<Product> productList;

    public ShowAllProductsInCategoryResponse(Set<ConstraintViolation<ShowAllProductsInCategoryRequest>> errors, List<Product> productList) {
        super(errors);
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
