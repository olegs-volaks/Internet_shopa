package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class DeliveryDatabase implements ProductDatabase {

    @Override
    public Long add(Product product) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
        return null;
    }

    @Override
    public List<Product> getList() {
        return null;
    }

    @Override
    public boolean isExist(Long id) {
        return false;
    }
}
