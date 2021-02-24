package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.services.category.AddCategoryService;
import eu.retarded.internetstore.database.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CategoryIntegrationTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private CategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
   }

   @Test
    void add_category_request() {
       AddCategoryService service = context.getBean(AddCategoryService.class);
       AddCategoryRequest request = new AddCategoryRequest("Book");
       AddCategoryRequest request1 = new AddCategoryRequest("BookMagma");
       AddCategoryRequest request2 = new AddCategoryRequest("Book2222");
       service.execute(request);
       service.execute(request1);
       service.execute(request2);
       assertThat(categoryRepository.findAll().size()).isEqualTo(3);

   }

   @Test
    void delete_category_request() {

   }

   @Test
    void delete_all_category_request() {

   }

   @Test
    void get_category_by_id() {

   }

   @Test
    void get_category_list() {

   }

   @Test
    void update_category_request() {

   }

}
