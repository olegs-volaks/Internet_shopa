package eu.retarded.internetstore.acceptancetests;

import eu.retarded.internetstore.config.applicationConfiguration;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
import eu.retarded.internetstore.core.services.product.AddProductService;
import eu.retarded.internetstore.core.services.product.DeleteProductService;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import eu.retarded.internetstore.core.services.product.SearchProductService;
import eu.retarded.internetstore.database.ProductDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {applicationConfiguration.class})
public class AcceptanceTest1 {

    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(applicationConfiguration.class);
    }

    @Test
    void add_product_request() {

        AddProductService service = context.getBean(AddProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        service.execute(request);
        service.execute(request1);
        service.execute(request2);
        assertThat(database.getList().size()).isEqualTo(3);
    }

    @Test
    void delete_product_request() {

        AddProductService addProductService = context.getBean(AddProductService.class);
        DeleteProductService deleteProductService = context.getBean(DeleteProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        deleteProductService.execute(new DeleteProductRequest(1));
        deleteProductService.execute(new DeleteProductRequest(2));
        assertThat(database.getList().size()).isEqualTo(1);
        assertThat(database.getById(1).isEmpty()).isTrue();
        assertThat(database.getById(2).isEmpty()).isTrue();
        assertThat(database.getById(3).isEmpty()).isFalse();
    }

    @Test
    void show_all_product_request() {

        AddProductService addProductService = context.getBean(AddProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        assertThat(database.getList().size()).isEqualTo(3);

    }

    @Test
    void get_product_by_id_request() {
        //context = new AnnotationConfigApplicationContext(applicationConfiguration.class);
        AddProductService addProductService = context.getBean(AddProductService.class);
        GetProductByIdService getProductByIdService = context.getBean(GetProductByIdService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        getProductByIdService.execute(new GetProductByIdRequest(2));
        getProductByIdService.execute(new GetProductByIdRequest(3));
        assertThat(database.getList().size()).isEqualTo(3);
        assertThat(database.getById(2).isEmpty()).isFalse();
        assertThat(database.getById(1).isEmpty()).isFalse();
        assertThat(database.getById(4).isEmpty()).isTrue();
    }

    @Test
    void search_product_request() {
        //context = new AnnotationConfigApplicationContext(applicationConfiguration.class);
        AddProductService addProductService = context.getBean(AddProductService.class);
        SearchProductService searchProductService = context.getBean(SearchProductService.class);
        ProductDatabase database = context.getBean(ProductDatabase.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        AddProductRequest request3 = new AddProductRequest("Samsung", "Dishwasher", 345.0);
        AddProductRequest request4 = new AddProductRequest("Samsung", "Oven", 325.99);
        AddProductRequest request5 = new AddProductRequest("Sony", "Fridge", 238.00);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        addProductService.execute(request3);
        addProductService.execute(request4);
        addProductService.execute(request5);
        searchProductService.execute(new SearchProductRequest("Apple", "Iphone XRMax"));
        searchProductService.execute(new SearchProductRequest("Sony", "Fridge"));
        searchProductService.execute(new SearchProductRequest("Samsung", "Dishwasher"));
        assertThat(database.getList().size()).isEqualTo(4);
    }

    @Test
    void add_product_validator_request() {
        //context = new AnnotationConfigApplicationContext(applicationConfiguration.class);
        AddProductService addProductService = context.getBean(AddProductService.class);
        AddProductRequest request = new AddProductRequest("XR", "Macbook", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "TV", 500.0);
        AddProductRequest request3 = new AddProductRequest("Samsung", "Dishwasher", 345.0);
        AddProductRequest request4 = new AddProductRequest("Samsung", "Oven", 325.99);
        AddProductRequest request5 = new AddProductRequest("Sony", "Fridge", 0);
        AddProductResponse response = addProductService.execute(request);
        AddProductResponse response1 = addProductService.execute(request2);
        AddProductResponse response2 = addProductService.execute(request5);
        AddProductResponse response3 = addProductService.execute(request1);
        AddProductResponse response4 = addProductService.execute(request3);
        AddProductResponse response5 = addProductService.execute(request4);
        assertThat(response.hasErrors()).isTrue();
        assertThat(response1.hasErrors()).isTrue();
        assertThat(response2.hasErrors()).isTrue();
        assertThat(response3.hasErrors()).isFalse();
        assertThat(response4.hasErrors()).isFalse();
        assertThat(response5.hasErrors()).isTrue();
    }
}
