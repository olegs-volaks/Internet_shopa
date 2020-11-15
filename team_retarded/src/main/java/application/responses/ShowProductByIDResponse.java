package application.responses;
import application.items.Product;

import java.util.List;
import java.util.Optional;

public class ShowProductByIDResponse  extends CoreResponse{

    private Optional<Product> product;

    public ShowProductByIDResponse() {
        super();
    }

    public ShowProductByIDResponse(List<CoreError> errors) {
        super(errors);
    }

    public ShowProductByIDResponse(Optional<Product> product) {
        this.product = product;
    }

    public Optional<Product> getProduct() {
        return product;
    }
}
