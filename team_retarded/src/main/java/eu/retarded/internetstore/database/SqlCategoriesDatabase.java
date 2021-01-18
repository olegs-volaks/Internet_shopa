package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
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
        jdbcTemplate.update("DELETE FROM product_category WHERE name = `?`", name);
        return true;
    }

    @Override
    public boolean removeCategory(Long id) {
        jdbcTemplate.update("DELETE FROM product_category WHERE id = ?", id);
        return true;
    }

    @Override
    public void removeCategory(Predicate<ProductCategory> predicate) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<ProductCategory> getCategoryList() {
        return null;
    }

    @Override
    public Optional<ProductCategory> getCategory(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean isExist(Long id) {
        return false;
    }
}
