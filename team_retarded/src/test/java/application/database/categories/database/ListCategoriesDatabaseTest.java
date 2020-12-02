package application.database.categories.database;

import application.database.categories.category.ProductListCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ListCategoriesDatabaseTest {

    private ListCategoriesDatabase subject;

    @BeforeEach
    void setUp() {
        subject = new ListCategoriesDatabase();
    }

    @Test
    void add_category() {
        assertThat(subject.addCategory(new ProductListCategory("name1"))).isEqualTo(1);
        subject.addCategory(new ProductListCategory("name2"));
        subject.addCategory(new ProductListCategory("name3"));
        assertThat(subject.getCategoryList().size()).isEqualTo(3);
    }

    @Test
    void remove_category_by_name() {
        subject.addCategory(new ProductListCategory("name1"));
        subject.addCategory(new ProductListCategory("name2"));
        subject.addCategory(new ProductListCategory("name3"));
        subject.removeCategory("name2");
        assertThat(subject.getCategoryList().size()).isEqualTo(2);
        assertThat(subject.getCategoryList())
                .noneMatch(productListCategory -> productListCategory.getName().equals("name2"));
    }

    @Test
    void remove_category_by_id() {
        subject.addCategory(new ProductListCategory("name1"));
        subject.addCategory(new ProductListCategory("name2"));
        subject.addCategory(new ProductListCategory("name3"));
        subject.removeCategory(2);
        assertThat(subject.getCategoryList().size()).isEqualTo(2);
        assertThat(subject.getCategoryList())
                .noneMatch(productListCategory -> productListCategory.getId() == 2);
    }

    @Test
    void remove_category_by_predicate() {
        subject.addCategory(new ProductListCategory("name1"));
        subject.addCategory(new ProductListCategory("name2"));
        subject.addCategory(new ProductListCategory("name3"));
        subject.removeCategory(productListCategory -> productListCategory.getId() == 1 ||
                productListCategory.getName().equals("name2"));
        assertThat(subject.getCategoryList().size()).isEqualTo(1);
        assertThat(subject.getCategoryList())
                .noneMatch(productListCategory -> productListCategory.getId() == 1 ||
                        productListCategory.getName().equals("name2"));
    }

    @Test
    void clear() {
        subject.addCategory(new ProductListCategory("name1"));
        subject.addCategory(new ProductListCategory("name2"));
        subject.addCategory(new ProductListCategory("name3"));
        subject.clear();
        assertThat(subject.getCategoryList()).isEmpty();
    }

    @Test
    void get_category() {
        subject.addCategory(new ProductListCategory("name1"));
        subject.addCategory(new ProductListCategory("name2"));
        subject.addCategory(new ProductListCategory("name3"));
        Optional<ProductListCategory> result = subject.getCategory(2);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).matches(productListCategory -> productListCategory.getName().equals("name2") ||
                productListCategory.getId() == 2);
    }
}