package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            Product product = new Product(name, description, price);
            product.setId(rs.getLong("id"));
            return product;
    }
}
