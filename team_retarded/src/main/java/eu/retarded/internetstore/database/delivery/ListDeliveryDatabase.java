package eu.retarded.internetstore.database.delivery;

import eu.retarded.internetstore.core.domain.Delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ListDeliveryDatabase implements DeliveryDatabase {

    private final List<Delivery> deliveryDatabase = new ArrayList<>();
    private Long id = 0L;


    @Override
    public Long add(Delivery delivery) {
        id++;
        delivery.setId(id);
        deliveryDatabase.add(delivery);
        return id;
    }


    @Override
    public boolean delete(Long id) {
        return deliveryDatabase.removeIf(delivery -> delivery.getId().equals(id));

    }

    @Override
    public void clear() {
        deliveryDatabase.clear();
    }

    @Override
    public Optional<Delivery> getById(Long id) {
        return deliveryDatabase.stream()
                .filter(delivery -> delivery.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Delivery> filter(Predicate<Delivery> predicate) {
        return deliveryDatabase.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Delivery> getList() {
        return deliveryDatabase;
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }
}
