package application.responses;
import application.items.Product;
import java.util.List;
import java.util.function.Predicate;

public class FilterProductResponse  extends CoreResponse{

    private final Predicate<Product> product;

    public FilterProductResponse(Predicate<Product> product) {
        this.product = product;
    }

    public FilterProductResponse(List<CoreError> errors, Predicate<Product> product) {
        super(errors);
        this.product = product;
    }

    public Predicate<Product> getProduct() {
        return product;
    }
}
