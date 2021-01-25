package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.config.ApplicationConfiguration;
import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.services.category.AddCategoryService;
import eu.retarded.internetstore.core.services.category.DeleteCategoryService;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class CategoryIntegrationTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private CategoriesDatabase categoriesDatabase;

    @BeforeEach
    void setUp() {
        categoriesDatabase.clear();
    }

    @Test
    void add_category_test() {
        AddCategoryService addCategoryService = context.getBean(AddCategoryService.class);
        long id1 = addCategoryService.execute(new AddCategoryRequest("First")).getCategoryId();
        long id2 = addCategoryService.execute(new AddCategoryRequest("Second")).getCategoryId();
        long id3 = addCategoryService.execute(new AddCategoryRequest("Third")).getCategoryId();
        assertThat(id1).isLessThan(id2);
        assertThat(id2).isLessThan(id3);
        List<Category> result = categoriesDatabase.getCategoryList();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void delete_category_by_id_test() {
        AddCategoryService addCategoryService = context.getBean(AddCategoryService.class);
        DeleteCategoryService deleteCategoryService = context.getBean(DeleteCategoryService.class);
        long id1 = addCategoryService.execute(new AddCategoryRequest("First")).getCategoryId();
        long id2 = addCategoryService.execute(new AddCategoryRequest("Second")).getCategoryId();
        long id3 = addCategoryService.execute(new AddCategoryRequest("Third")).getCategoryId();
        boolean firstResult = deleteCategoryService.execute(new DeleteCategoryRequest(id2)).isDeleted();
        boolean secondResult = deleteCategoryService.execute(new DeleteCategoryRequest(id3 + 1)).isDeleted();
        assertThat(firstResult).isTrue();
        assertThat(secondResult).isFalse();
        List<Category> resultList = categoriesDatabase.getCategoryList();
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList).noneMatch(category -> category.getId() == id2);
    }
}
