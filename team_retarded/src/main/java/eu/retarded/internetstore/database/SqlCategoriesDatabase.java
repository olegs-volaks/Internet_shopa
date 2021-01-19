package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.ProductCategory;
import eu.retarded.internetstore.core.domain.row_mapper.ProductCategoryMapper;
import eu.retarded.internetstore.core.domain.row_mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class SqlCategoriesDatabase implements CategoriesDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long addCategory(ProductCategory ListCategory) {
        jdbcTemplate.update("INSERT INTO product_category(name) VALUES (?)", ListCategory.getName());
        return jdbcTemplate.queryForObject("SELECT id FROM product_category WHERE id=(SELECT max(id) FROM product_category)",
                Long.class);
    }

    @Override
    public boolean removeCategory(String name) {
        try {
            jdbcTemplate.update("DELETE FROM product_category WHERE name = `?`", name);
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    @Override
    public boolean removeCategory(Long id) {
        try {
            jdbcTemplate.update("DELETE FROM product_category WHERE id = ?", id);
            return true;
        } catch (DataAccessException ex) {
            return false;
        }
    }

    @Override
    public void removeCategory(Predicate<ProductCategory> predicate) {

    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM product_category");
    }

    @Override
    public List<ProductCategory> getCategoryList() {
        List<ProductCategory> categories = jdbcTemplate.query("SELECT * FROM product_category", new ProductCategoryMapper());
        categories.forEach(productCategory -> {
            List<Product> products = jdbcTemplate.query("SELECT * FROM products WHERE category_id = ?",
                    new ProductMapper(), productCategory.getId());
            productCategory.setProducts(products);
        });
        return categories;
    }

    @Override
    public Optional<ProductCategory> getCategory(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM product_category WHERE id = ?",
                new ProductCategoryMapper(), id));
    }

    @Override
    public boolean isExist(Long id) {
        return getCategory(id).isPresent();
    }
}
