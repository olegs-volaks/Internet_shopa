package eu.retarded.internetstore.database.categories.category;

import eu.retarded.internetstore.core.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductListCategoryTest {

    private ProductListCategory subject;

    @BeforeEach
    void setUp() {
        subject = new ProductListCategory("subject");
    }

    @Test
    void add_product_test() {
        subject.add(new Product("name1", "desc1", 12.6));
        subject.add(new Product("name2", "desc2", 12.6));
        assertThat(subject.getList().size()).isEqualTo(2);
    }

    @Test
    void remove_from_category() {
        Product product1 = new Product("name1", "desc1", 12.6);
        Product product2 = new Product("name2", "desc2", 12.6);
        Product product3 = new Product("name3", "desc3", 12.6);
        Product product4 = new Product("name4", "desc4", 12.6);
        subject.add(product1);
        subject.add(product2);
        subject.add(product3);
        subject.add(product4);
        subject.remove(product2);
        subject.remove(product3);
        assertThat(subject.getList().size()).isEqualTo(2);
    }

    @Test
    void remove_from_category_by_predicate() {
        Product product1 = new Product("name1", "desc1", 12.6);
        Product product2 = new Product("name2", "desc2", 12.6);
        Product product3 = new Product("name3", "desc3", 12.6);
        Product product4 = new Product("name4", "desc4", 12.6);
        subject.add(product1);
        subject.add(product2);
        subject.add(product3);
        subject.add(product4);
        subject.remove(product -> product.getName().equals("name2") || product.getName().equals("name3"));
        assertThat(subject.getList().size()).isEqualTo(2);
        Assertions.assertThat(subject.getList()).noneMatch(product -> product.getName().equals("name2") ||
                product.getName().equals("name3"));
    }
}