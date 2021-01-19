package eu.retarded.internetstore.core.domain.row_mapper;

import eu.retarded.internetstore.core.domain.ProductCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryMapper implements RowMapper<ProductCategory> {
    @Override
    public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCategory productCategory = new ProductCategory(rs.getString("name"));
        productCategory.setId(rs.getLong("id"));
        return productCategory;
    }
}
