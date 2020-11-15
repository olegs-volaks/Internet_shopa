package application.requests;

import application.items.Product;

import java.util.function.Predicate;

public class FilterProductRequests {

    private final Predicate<Product> predicate;

    public FilterProductRequests(Predicate<Product> predicate) {
        this.predicate = predicate;
    }

    public Predicate<Product> getPredicate() {
        return predicate;
    }
}
