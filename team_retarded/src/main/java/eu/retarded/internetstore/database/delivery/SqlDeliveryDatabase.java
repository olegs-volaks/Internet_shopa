package eu.retarded.internetstore.database.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.domain.row_mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Component
public class SqlDeliveryDatabase implements DeliveryDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Long add(Delivery delivery) {
        jdbcTemplate.update("INSERT INTO delivery(title, region, price)" +
                        "VALUES (?, ? , ?)",
                delivery.getTitle(), delivery.getRegion(), delivery.getPrice());
        return jdbcTemplate.queryForObject("SELECT id FROM delivery WHERE id=(SELECT max(id) FROM delivery", // ид или title?
                Long.class);
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("DELETE FROM delivery WHERE id=?", id) == 1;
    }

    @Override
    public boolean delete(Predicate<Delivery> predicate) {
        List<Delivery> deliveries = jdbcTemplate.query("SELECT * FROM delivery", new DeliveryMapper());
        return deliveries.removeIf(predicate);
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM delivery");

    }

    @Override
    public Optional<Delivery> getById(Long id) {
        Delivery delivery;
        try {
            delivery = jdbcTemplate.queryForObject("SELECT * FROM delivery WHERE id = ?", new DeliveryMapper(), id);
        } catch (IncorrectResultSizeDataAccessException exception) {
            delivery = null;
        }
        return Optional.ofNullable(delivery);
    }

    @Override
    public List<Delivery> filter(Predicate<Delivery> predicate) {
        List<Delivery> deliveries = jdbcTemplate.query("SELECT * FROM delivery", new DeliveryMapper());
        return deliveries.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Delivery> getList() {
        return jdbcTemplate.query("SELECT * FROM delivery", new DeliveryMapper());
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }
}
