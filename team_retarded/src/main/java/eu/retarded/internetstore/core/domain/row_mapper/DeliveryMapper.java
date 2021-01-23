package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.Delivery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryMapper implements RowMapper<Delivery> {

    @Override
    public Delivery mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        String title = resultSet.getString("title");
        String region = resultSet.getString("region");
        double price = resultSet.getDouble("price");
        Delivery delivery = new Delivery(title, region, price);
        delivery.setId(resultSet.getLong("id"));
        return delivery;
    }
}
