package application.responses;
import application.items.Product;
import java.util.List;

public class ShowAllProductResponse extends CoreResponse {

    private final List<Product> showAllProducts;

    public ShowAllProductResponse(List<Product> showAllProducts) {
        this.showAllProducts = showAllProducts;
    }

    public ShowAllProductResponse(List<CoreError> errors, List<Product> showAllProducts) {
        super(errors);
        this.showAllProducts = showAllProducts;
    }

    public List<Product> getShowAllProducts() {
        return showAllProducts;
    }
}
