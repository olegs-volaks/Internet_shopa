package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class ShowAllProductsInCategoryResponse extends CoreResponse {

    private List <Product> productList;

    public ShowAllProductsInCategoryResponse(List <Product> productList,List<CoreError> errors) {
        super(errors);
        this.productList = productList;
    }

    public List <Product> getProductList() {
        return productList;
    }
}
