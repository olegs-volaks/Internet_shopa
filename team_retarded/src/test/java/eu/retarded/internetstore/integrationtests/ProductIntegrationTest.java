package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
import eu.retarded.internetstore.core.services.product.AddProductService;
import eu.retarded.internetstore.core.services.product.DeleteProductService;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import eu.retarded.internetstore.core.services.product.SearchProductService;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private ProductDatabase productDatabase;


    @BeforeEach
    void setUp() {
        productDatabase.clear();
    }

    @Test
    void add_product_request() {

        AddProductService service = context.getBean(AddProductService.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro555", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax55", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 555", 500.0);
        service.execute(request);
        service.execute(request1);
        service.execute(request2);
        assertThat(productDatabase.getList().size()).isEqualTo(3);
    }

    @Test
    void delete_product_request() {

        AddProductService addProductService = context.getBean(AddProductService.class);
        DeleteProductService deleteProductService = context.getBean(DeleteProductService.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        long id1 = addProductService.execute(request).getProductId();
        long id2 = addProductService.execute(request1).getProductId();
        long id3 = addProductService.execute(request2).getProductId();
        deleteProductService.execute(new DeleteProductRequest(id1));
        deleteProductService.execute(new DeleteProductRequest(id2));
        List<Product> productList = productDatabase.getList();
        assertThat(productDatabase.getList().size()).isEqualTo(1);
        assertThat(productDatabase.getById(id1).isEmpty()).isTrue();
        assertThat(productDatabase.getById(id2).isEmpty()).isTrue();
        assertThat(productDatabase.getById(id3).isEmpty()).isFalse();
    }

    @Test
    void show_all_product_request() {

        AddProductService addProductService = context.getBean(AddProductService.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        assertThat(productDatabase.getList().size()).isEqualTo(3);

    }

    @Test
    void get_product_by_id_request() {

        AddProductService addProductService = context.getBean(AddProductService.class);
        GetProductByIdService getProductByIdService = context.getBean(GetProductByIdService.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        long id1 = addProductService.execute(request).getProductId();
        long id2 = addProductService.execute(request1).getProductId();
        long id3 = addProductService.execute(request2).getProductId();
        getProductByIdService.execute(new GetProductByIdRequest(id2));
        getProductByIdService.execute(new GetProductByIdRequest(id3));
        assertThat(productDatabase.getList().size()).isEqualTo(3);
        assertThat(productDatabase.getById(id2).isEmpty()).isFalse();
        assertThat(productDatabase.getById(id1).isEmpty()).isFalse();
        assertThat(productDatabase.getById(id3).isEmpty()).isFalse();
    }

    @Test
    void search_product_request() {

        AddProductService addProductService = context.getBean(AddProductService.class);
        SearchProductService searchProductService = context.getBean(SearchProductService.class);
        AddProductRequest request = new AddProductRequest("Apple", "MackBook-Pro", 150.0);
        AddProductRequest request1 = new AddProductRequest("Apple", "Iphone XRMax", 899.99);
        AddProductRequest request2 = new AddProductRequest("Sony", "Playstation 5", 500.0);
        AddProductRequest request3 = new AddProductRequest("Samsung", "Dishwasher", 345.0);
        AddProductRequest request4 = new AddProductRequest("Samsung", "Oven123467890", 325.99);
        AddProductRequest request5 = new AddProductRequest("Sony", "Fridge12346789", 238.00);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        addProductService.execute(request3);
        addProductService.execute(request4);
        addProductService.execute(request5);
        assertThat(searchProductService.execute(new SearchProductRequest("i","ASC",1)).getProducts().size()).isEqualTo(4);
        assertThat(searchProductService.execute(new SearchProductRequest("g","ASC",1)).getProducts().size()).isEqualTo(3);
       assertThat(searchProductService.execute(new SearchProductRequest("Sony","ASC",1)).getProducts().size()).isEqualTo(2);
        searchProductService.execute(new SearchProductRequest("Samsung","ASC",1));
        assertThat(productDatabase.search("5","ASC",1).size()).isEqualTo(1);
    }

    @Test
    void add_product_validator_request() {

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
