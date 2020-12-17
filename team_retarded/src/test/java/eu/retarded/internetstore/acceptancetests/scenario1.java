package eu.retarded.internetstore.acceptancetests;

import eu.retarded.internetstore.config.applicationConfiguration;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
import eu.retarded.internetstore.core.services.product.AddProductService;
import eu.retarded.internetstore.core.services.product.DeleteProductService;
import eu.retarded.internetstore.database.ProductDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


public class scenario1 {

    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(applicationConfiguration.class);
    }

    @Test
    void test1() {

        AddProductService service = context.getBean(AddProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request1 = new AddProductRequest("name1", "description123123", 123.1);
        AddProductRequest request2 = new AddProductRequest("name1", "description123123", 123.1);
        service.execute(request1);
        service.execute(request2);
        assertThat(database.getList().size()).isEqualTo(2);
    }

    @Test
    void test2() {

        AddProductService addService = context.getBean(AddProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request1 = new AddProductRequest("name1", "description123123", 123.1);
        AddProductRequest request2 = new AddProductRequest("name2", "description456456", 123.1);
        addService.execute(request1);
        addService.execute(request2);
        DeleteProductService deleteService = context.getBean(DeleteProductService.class);
        deleteService.execute(new DeleteProductRequest(2));
        assertThat(database.getList().size()).isEqualTo(1);
        assertThat(database.getById(2).isEmpty()).isTrue();
        assertThat(database.getById(1).isEmpty()).isFalse();
    }

    @Test
    void test3() {

        AddProductService addService = context.getBean(AddProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest addRequest1 = new AddProductRequest("name1", "description123123", 123.1);
        AddProductRequest addRequest2 = new AddProductRequest("name2", "description456456", 123.1);
        AddProductRequest addRequest3 = new AddProductRequest("name3", "description456456", 123.1);
        addService.execute(addRequest1);
        addService.execute(addRequest2);
        DeleteProductService deleteService = context.getBean(DeleteProductService.class);
        addService.execute(addRequest3);
        deleteService.execute(new DeleteProductRequest(2));
        assertThat(database.getById(2).isEmpty()).isTrue();
        assertThat(database.getList().size()).isEqualTo(2);
        assertThat(database.getById(3).get().getName()).isEqualTo("name3");
    }

    @Test
    void test4() {

        AddProductService addService = context.getBean(AddProductService.class);
        AddProductRequest addRequest1 = new AddProductRequest("nam", "description123123", 123.1);
        AddProductResponse response = addService.execute(addRequest1);
        Assertions.assertThat(response.hasErrors()).isTrue();
    }
}
