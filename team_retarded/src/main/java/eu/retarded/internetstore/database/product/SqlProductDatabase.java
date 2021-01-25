package eu.retarded.internetstore.database.product;


import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.row_mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

//@Component
public class SqlProductDatabase implements ProductDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long add(Product product) {
        jdbcTemplate.update("INSERT INTO products(name, description, price) VALUES (?,?,?)", product.getName(),
                product.getDescription(), product.getPrice());
        return jdbcTemplate.queryForObject("SELECT id FROM products WHERE id=(SELECT max(id) FROM products)",
                Long.class);
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update("DELETE FROM products WHERE id=?", id) == 1;
    }


    public boolean delete(Predicate<Product> predicate) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM products", new ProductMapper());
        return products.removeIf(predicate);
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM products");
    }

    @Override
    public Optional<Product> getById(Long id) {
        Product product;
        try {
            product = jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", new ProductMapper(), id);
        } catch (IncorrectResultSizeDataAccessException exception) {
            product = null;
        }
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM products", new ProductMapper());
        return products.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Product> getList() {
        return jdbcTemplate.query("SELECT * FROM products", new ProductMapper());
    }

   /* private void deleteFromCategories(Predicate<Product> predicate) {
        List<ProductCategory> categories = jdbcTemplate.query("SELECT * FROM product_categories", new ProductCategoryMapper());
        categories.forEach(category -> category.remove(predicate));
    }*/

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }

    @Override
    public boolean addProductToCategory(Long productId, Long categoryId) {
        return jdbcTemplate.update("UPDATE products SET category_id = ? WHERE id = ? ", categoryId, productId) == 1;
    }

    @Override
    public boolean removeProductFromCategory(Long productId) {
        return jdbcTemplate.update("UPDATE products SET category_id=null WHERE id = ? ", productId) == 1;
    }

}
