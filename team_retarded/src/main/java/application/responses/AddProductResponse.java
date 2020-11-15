package application.responses;
import application.items.Product;
import java.util.List;

public class AddProductResponse  extends CoreResponse {

    private  Product newProduct;

    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductResponse (Product newProduct) {
        this.newProduct = newProduct;
    }

    public Product getNewProduct() {
        return newProduct;
    }
}
