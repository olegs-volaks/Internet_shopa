package eu.retarded.internetstore.acceptancetests;

import eu.retarded.internetstore.config.ApplicationConfiguration;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
import eu.retarded.internetstore.core.services.product.AddProductService;
import eu.retarded.internetstore.core.services.product.DeleteProductService;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class scenario1 {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private ProductDatabase productDatabase;

    @BeforeEach
    void setUp() {
        productDatabase.clear();
    }

    @Test
    void test1() {

        AddProductService service = context.getBean(AddProductService.class);
        AddProductRequest request1 = new AddProductRequest("name1", "description123123", 123.1);
        AddProductRequest request2 = new AddProductRequest("name1", "description123123", 123.1);
        service.execute(request1);
        service.execute(request2);
        assertThat(productDatabase.getList().size()).isEqualTo(2);
    }

    @Test
    void test2() {

        AddProductService addService = context.getBean(AddProductService.class);
        AddProductRequest request1 = new AddProductRequest("name1", "description123123", 123.1);
        AddProductRequest request2 = new AddProductRequest("name2", "description456456", 123.1);
        addService.execute(request1);
        addService.execute(request2);
        DeleteProductService deleteService = context.getBean(DeleteProductService.class);
        deleteService.execute(new DeleteProductRequest(2));
        assertThat(productDatabase.getList().size()).isEqualTo(2);
        assertThat(productDatabase.getById(2L).isEmpty()).isTrue();
        assertThat(productDatabase.getById(1L).isEmpty()).isTrue();
    }

    @Test
    void test3() {

        AddProductService addService = context.getBean(AddProductService.class);
        AddProductRequest addRequest1 = new AddProductRequest("name1", "description123123", 123.1);
        AddProductRequest addRequest2 = new AddProductRequest("name2", "description456456", 123.1);
        AddProductRequest addRequest3 = new AddProductRequest("name3", "description456456", 123.1);
        long id1 =  addService.execute(addRequest1).getProductId();
        long id2 =  addService.execute(addRequest2).getProductId();
        long id3 = addService.execute(addRequest3).getProductId();
        DeleteProductService deleteService = context.getBean(DeleteProductService.class);
        deleteService.execute(new DeleteProductRequest(id2));
        assertThat(productDatabase.getById(id2).isEmpty()).isTrue();
        assertThat(productDatabase.getList().size()).isEqualTo(2);
        assertThat(productDatabase.getById(id3).get().getName()).isEqualTo("name3");
    }

    @Test
    void test4() {

        AddProductService addService = context.getBean(AddProductService.class);
        AddProductRequest addRequest1 = new AddProductRequest("nm", "description123123", 123.1);
        AddProductResponse response = addService.execute(addRequest1);
        Assertions.assertThat(response.hasErrors()).isTrue();
    }
}
