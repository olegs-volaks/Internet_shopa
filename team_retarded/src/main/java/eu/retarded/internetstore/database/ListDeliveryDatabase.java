package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListDeliveryDatabase  implements  DeliveryDatabase{

    private String title;
    private final List<Delivery> deliveryDatabase = new ArrayList<>();
    private Long id =0L;


    @Override
    public Long add(Delivery delivery) {
        id++;
        delivery.setTitle(title);
        deliveryDatabase.add(delivery);
        return null;
    }

    @Override
    public boolean delete(Long id) {
        delete(delivery -> delivery.getTitle().equals(title));
        deliveryDatabase.removeIf(delivery -> delivery.getTitle().equals(title)); // не уверен , думаю вместо title надо id
        return false;
    }

    @Override
    public boolean delete(Predicate<Delivery> predicate) {
        return false;
    }

    @Override
    public void clear() {
        deliveryDatabase.clear();
    }

    @Override
    public Optional<Delivery> getById(Long id) {
        return deliveryDatabase.stream()
                .filter(delivery -> delivery.getTitle().equals(title)) // тут тоже самое ;/
                .findAny();
    }

    @Override
    public List<Delivery> filter(Predicate<Delivery> predicate) {
        return deliveryDatabase.stream()
                .filter(predicate)
                .collect(Collectors.toList());
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
