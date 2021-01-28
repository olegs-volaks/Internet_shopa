package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.database.category.ListCategoriesDatabase;
import org.assertj.core.api.Assertions;
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
        assertThat(subject.addCategory(new Category("name1"))).isEqualTo(1);
        subject.addCategory(new Category("name2"));
        subject.addCategory(new Category("name3"));
        assertThat(subject.getCategoryList().size()).isEqualTo(3);
    }

    @Test
    void remove_category_by_name() {
        subject.addCategory(new Category("name1"));
        subject.addCategory(new Category("name2"));
        subject.addCategory(new Category("name3"));
        subject.removeCategory("name2");
        assertThat(subject.getCategoryList().size()).isEqualTo(2);
        Assertions.assertThat(subject.getCategoryList())
                .noneMatch(productListCategory -> productListCategory.getName().equals("name2"));
    }

    @Test
    void remove_category_by_id() {
        subject.addCategory(new Category("name1"));
        subject.addCategory(new Category("name2"));
        subject.addCategory(new Category("name3"));
        subject.removeCategory(2L);
        assertThat(subject.getCategoryList().size()).isEqualTo(2);
        Assertions.assertThat(subject.getCategoryList())
                .noneMatch(productListCategory -> productListCategory.getId() == 2);
    }

    @Test
    void clear() {
        subject.addCategory(new Category("name1"));
        subject.addCategory(new Category("name2"));
        subject.addCategory(new Category("name3"));
        subject.clear();
        Assertions.assertThat(subject.getCategoryList()).isEmpty();
    }

    @Test
    void get_category() {
        subject.addCategory(new Category("name1"));
        subject.addCategory(new Category("name2"));
        subject.addCategory(new Category("name3"));
        Optional<Category> result = subject.getCategory(2L);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).matches(productListCategory -> productListCategory.getName().equals("name2") ||
                productListCategory.getId() == 2);
    }
}