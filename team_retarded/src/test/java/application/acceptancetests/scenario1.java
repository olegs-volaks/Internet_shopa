package application.acceptancetests;

import application.core.requests.product.AddProductRequest;
import application.core.requests.product.DeleteProductRequest;
import application.core.responses.product.AddProductResponse;
import application.core.services.product.AddProductService;
import application.core.services.product.DeleteProductService;
import application.database.ProductDatabase;
import com.retarded.di.ApplicationContext;
import com.retarded.di.DIApplicationContextBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class scenario1 {

    //private ApplicationContext context = new ApplicationContext();
    private final ApplicationContext context = new DIApplicationContextBuilder().build("application");

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
        assertThat(response.hasErrors()).isTrue();
    }
}
