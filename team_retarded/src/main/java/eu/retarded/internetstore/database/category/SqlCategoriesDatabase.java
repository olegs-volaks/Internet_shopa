package eu.retarded.internetstore.database.category;

import eu.retarded.internetstore.core.domain.ProductCategory;
import eu.retarded.internetstore.core.domain.row_mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
        jdbcTemplate.update("INSERT INTO product_categories(name) VALUES (?)", ListCategory.getName());
        return jdbcTemplate.queryForObject("SELECT id FROM product_categories WHERE id=(SELECT max(id) FROM product_categories)",
                Long.class);
    }

    @Override
    public boolean removeCategory(String name) {
        return jdbcTemplate.update("DELETE FROM product_categories WHERE name = ?", name) == 1;
    }

    @Override
    public boolean removeCategory(Long id) {
        return jdbcTemplate.update("DELETE FROM product_categories WHERE id = ?", id) == 1;
    }

    @Override
    public void removeCategory(Predicate<ProductCategory> predicate) {
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM product_categories");
    }

    @Override
    public List<ProductCategory> getCategoryList() {
        return jdbcTemplate.query("SELECT * FROM product_categories", new ProductCategoryMapper());
    }

    @Override
    public Optional<ProductCategory> getCategory(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM product_categories WHERE id = ?",
                    new ProductCategoryMapper(), id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isExist(Long id) {
        return getCategory(id).isPresent();
    }
}
