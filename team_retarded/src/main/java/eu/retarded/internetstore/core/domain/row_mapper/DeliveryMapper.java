package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.Delivery;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryMapper implements RowMapper<Delivery> {

    @Override
    public Delivery mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Delivery delivery = new Delivery();
        String title = resultSet.getString("title");
        String region = resultSet.getString("region");
        BigDecimal price = resultSet.getBigDecimal("price");
        delivery.setId(resultSet.getLong("id"));
        delivery.setTitle(title);
        delivery.setRegion(region);
        delivery.setPrice(price);
        return delivery;
    }
}
