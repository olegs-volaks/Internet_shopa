package application.requests;
import application.items.Product;
import java.util.List;
import java.util.function.Predicate;

public class FilterProductRequests {

    private Predicate<Product> predicate;

    public List<Product> FilterProductRequests(Predicate<Product> predicate) {
        this.predicate = predicate;
        return null;
    }

    public Predicate<Product> getPredicate() {
        return predicate;
    }
}
