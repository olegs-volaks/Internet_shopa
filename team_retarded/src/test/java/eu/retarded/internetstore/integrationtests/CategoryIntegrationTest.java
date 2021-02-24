package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.services.category.*;
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
        AddCategoryService addCategoryService = context.getBean(AddCategoryService.class);
        DeleteCategoryService deleteCategoryService = context.getBean(DeleteCategoryService.class);
        AddCategoryRequest request = new AddCategoryRequest("Book");
        AddCategoryRequest request2 = new AddCategoryRequest("Book222");
        AddCategoryRequest request3 = new AddCategoryRequest("Book333");
        AddCategoryRequest request4 = new AddCategoryRequest("Book444");
        Category id = addCategoryService.execute(request).getCategory();
        Category id2 = addCategoryService.execute(request2).getCategory();
        Category id3 = addCategoryService.execute(request3).getCategory();
        Category id4 = addCategoryService.execute(request4).getCategory();
        deleteCategoryService.execute(new DeleteCategoryRequest(id.getId()));
        deleteCategoryService.execute(new DeleteCategoryRequest(id2.getId()));
        deleteCategoryService.execute(new DeleteCategoryRequest(id3.getId()));
        assertThat(categoryRepository.findAll().size()).isEqualTo(1);

    }

    @Test
    void show_all_category_request() {
        AddCategoryService addCategoryService = context.getBean(AddCategoryService.class);
        AddCategoryRequest request = new AddCategoryRequest("Apple");
        AddCategoryRequest request2 = new AddCategoryRequest("Apple2");
        AddCategoryRequest request3 = new AddCategoryRequest("Apple3");
        addCategoryService.execute(request);
        addCategoryService.execute(request2);
        addCategoryService.execute(request3);
        assertThat(categoryRepository.findAll().size()).isEqualTo(3);

    }

    @Test
    void get_category_by_id() {
        AddCategoryService addCategoryService = context.getBean(AddCategoryService.class);
        GetCategoryByIdService getCategoryByIdService = context.getBean(GetCategoryByIdService.class);
        AddCategoryRequest request = new AddCategoryRequest("Apple");
        AddCategoryRequest request2 = new AddCategoryRequest("Apple2");
        AddCategoryRequest request3 = new AddCategoryRequest("Apple3");
        AddCategoryRequest request4 = new AddCategoryRequest("Apple4");
        Category id = addCategoryService.execute(request).getCategory();
        Category id2 = addCategoryService.execute(request2).getCategory();
        Category id3 = addCategoryService.execute(request3).getCategory();
        Category id4 = addCategoryService.execute(request4).getCategory();
        getCategoryByIdService.execute(new GetCategoryByIdRequest(id.getId()));
        getCategoryByIdService.execute(new GetCategoryByIdRequest(id2.getId()));
        getCategoryByIdService.execute(new GetCategoryByIdRequest(id3.getId()));
        getCategoryByIdService.execute(new GetCategoryByIdRequest(id4.getId()));
        assertThat(categoryRepository.existsById(id.getId())).isTrue();
        assertThat(categoryRepository.existsById(id2.getId())).isTrue();
        assertThat(categoryRepository.existsById(id3.getId())).isTrue();
        assertThat(categoryRepository.existsById(id4.getId())).isTrue();

    }

    @Test
    void update_category_request() {
        AddCategoryService addCategoryService = context.getBean(AddCategoryService.class);
        UpdateCategoryService updateCategoryService = context.getBean(UpdateCategoryService.class);
        Category id = addCategoryService.execute(new AddCategoryRequest("Book")).getCategory();
        updateCategoryService.execute(new UpdateCategoryRequest(id.getId(),"AppleBookMack"));
        Category result = categoryRepository.findById(id.getId()).get();
        Category expecting = new Category();
        expecting.setId(id.getId());
        expecting.setName("AppleBookMack");
        assertThat(result).isEqualTo(expecting);
    }
}
