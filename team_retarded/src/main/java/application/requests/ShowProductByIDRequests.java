package application.requests;
import application.items.Product;
import java.util.Optional;

public class ShowProductByIDRequests {

    private Long getById;

    public Optional<Product> ShowProductByIDRequests(Long getById) {
        this.getById = getById;
        return Optional.empty();
    }

    public Long getById() {
        return getById;
    }
}
