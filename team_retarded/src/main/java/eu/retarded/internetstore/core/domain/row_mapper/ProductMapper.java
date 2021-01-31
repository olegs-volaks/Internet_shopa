package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        String name = rs.getString("name");
        String description = rs.getString("description");
        BigDecimal price = rs.getBigDecimal("price");
        Category category = rs.getObject("category_id", Category.class);
        product.setId(rs.getLong("id"));
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
}
