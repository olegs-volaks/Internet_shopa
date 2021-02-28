package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.*;
import eu.retarded.internetstore.core.services.product.*;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.ProductRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void add_product_request() {
        AddProductService addProductService = context.getBean(AddProductService.class);
        AddProductRequest request = new AddProductRequest("Apple", "fruitadadadddddddddddddddddddddddafafafsgsgsgdgsgsg", 10, 1);
        AddProductRequest request2 = new AddProductRequest("Apple2", "fruitruitadadadddddddddddddddddddddddafafafsgsgsgdgsgsg", 12, 2);
        AddProductRequest request3 = new AddProductRequest("Apple3", "fruitruitadadadddddddddddddddddddddddafafafsgsgsgdgsgsg", 13, 3);
        AddProductRequest request4 = new AddProductRequest("Apple4", "fruitruitadadadddddddddddddddddddddddafafafsgsgsgdgsgsg", 14, 4);
        AddProductRequest request5 = new AddProductRequest("Apple5", "fruitruitadadadddddddddddddddddddddddafafafsgsgsgdgsgsg", 15, 5);
        addProductService.execute(request);
        addProductService.execute(request2);
        addProductService.execute(request3);
        addProductService.execute(request4);
        addProductService.execute(request5);
        assertThat(productRepository.findAll().size()).isEqualTo(5);
    }

    @Test
    void delete_product_request() {
        AddProductService addProductService = context.getBean(AddProductService.class);
        DeleteProductService deleteProductService = context.getBean(DeleteProductService.class);
        AddProductRequest request = new AddProductRequest("Apple", "fruitruitadadaddddaaaaaaadddddddddddddddddddafafafsgsgsgdgsgsg", 10, 1);
        AddProductRequest request2 = new AddProductRequest("Apple2", "fruitruitadadaddaaaadddddddddddddddddddddafafafsgsgsgdgsgsg", 12, 2);
        AddProductRequest request3 = new AddProductRequest("Apple3", "fruitruitadadaddaaaaadddddddddddddddddddddafafafsgsgsgdgsgsg", 13, 3);
        AddProductRequest request4 = new AddProductRequest("Apple4", "fruitruitadadaddaaaaadddddddddddddddddddddafafafsgsgsgdgsgsg", 14, 4);
        AddProductRequest request5 = new AddProductRequest("Apple5", "fruitruitadadaaaaaaadddddddddddddddddddddddafafafsgsgsgdgsgsg", 15, 5);
        Product id = addProductService.execute(request).getProduct();
        Product id2 = addProductService.execute(request2).getProduct();
        Product id3 = addProductService.execute(request3).getProduct();
        Product id4 = addProductService.execute(request4).getProduct();
        Product id5 = addProductService.execute(request5).getProduct();
        deleteProductService.execute(new DeleteProductRequest(id.getId()));
        deleteProductService.execute(new DeleteProductRequest(id2.getId()));
        deleteProductService.execute(new DeleteProductRequest(id3.getId()));
        assertThat(productRepository.findAll().size()).isEqualTo(2);

    }

    @Test
    void get_product_by_id_request() {
        AddProductService addProductService = context.getBean(AddProductService.class);
        GetProductByIdService getProductByIdService = context.getBean(GetProductByIdService.class);
        AddProductRequest request = new AddProductRequest("Apple", "ruitruitadadaddddaaaaaaadddddddddddddddddddafafafsgsgsgdgsgsgfruit", 10, 1);
        AddProductRequest request2 = new AddProductRequest("Apple2", "ruitruitadadaddddaaaaaaadddddddddddddddddddafafafsgsgsgdgsgsgfruit", 12, 2);
        AddProductRequest request3 = new AddProductRequest("Apple3", "fruitruitruitadadaddddaaaaaaadddddddddddddddddddafafafsgsgsgdgsgsg", 13, 3);
        AddProductRequest request4 = new AddProductRequest("Apple4", "fruitruitruitadadaddddaaaaaaadddddddddddddddddddafafafsgsgsgdgsgsg", 14, 4);
        AddProductRequest request5 = new AddProductRequest("Apple5", "fruitruitruitadadaddddaaaaaaadddddddddddddddddddafafafsgsgsgdgsgsg", 15, 5);
        Product id = addProductService.execute(request).getProduct();
        Product id2 = addProductService.execute(request2).getProduct();
        Product id3 = addProductService.execute(request3).getProduct();
        Product id4 = addProductService.execute(request4).getProduct();
        Product id5 = addProductService.execute(request5).getProduct();
        getProductByIdService.execute(new GetProductByIdRequest(id.getId()));
        getProductByIdService.execute(new GetProductByIdRequest(id2.getId()));
        getProductByIdService.execute(new GetProductByIdRequest(id3.getId()));
        getProductByIdService.execute(new GetProductByIdRequest(id4.getId()));
        getProductByIdService.execute(new GetProductByIdRequest(id5.getId()));
        assertThat(productRepository.existsById(id.getId())).isTrue();
        assertThat(productRepository.existsById(id2.getId())).isTrue();
        assertThat(productRepository.existsById(id3.getId())).isTrue();
        assertThat(productRepository.existsById(id4.getId())).isTrue();
        assertThat(productRepository.existsById(id5.getId())).isTrue();

    }

    @Test
    void update_product_request() {
        AddProductService addProductService = context.getBean(AddProductService.class);
        UpdateProductService updateProductService = context.getBean(UpdateProductService.class);
        Product id = addProductService.execute(new AddProductRequest("Apple","Iphone XS Max Pro 12, 240GB,Black Silver color",
                1500.0,15)).getProduct();
        updateProductService.execute(new UpdateProductRequest(id.getId(),"Apple12","IPHONE 12214444444GB silver black one,best phone ever",
                1200.00,145)).getProduct();
        Product result = productRepository.findById(id.getId()).get();
        Product expecting = new Product();
        expecting.setId(id.getId());
        expecting.setName("Apple12");
        expecting.setDescription("IPHONE 12214444444GB silver black one,best phone ever");
        expecting.setPrice(new BigDecimal("1200.00"));
        expecting.setCount(145);
        expecting.setStatus(1);
        assertThat(result).isEqualTo(expecting);
    }

  /*  @Test
    void add_product_validator_request() {
        AddProductService addProductService = context.getBean(AddProductService.class);
        AddProductRequest request = new AddProductRequest()
    } */

    @Test
    void clear_all_product_request() {
        AddProductService addProductService = context.getBean(AddProductService.class);
        ClearAllProductsService clearAllProductsService = context.getBean(ClearAllProductsService.class);
        AddProductRequest request = new AddProductRequest("Huawei","mobile-phone",450.0,12);
        AddProductRequest request1 = new AddProductRequest("Apple","mobile-phone",750.0,10);
        AddProductRequest request2 = new AddProductRequest("Nokia","mobile-phone",350.0,125);
        AddProductRequest request3 = new AddProductRequest("Sony","mobile-phone",250.0,5);
        addProductService.execute(request);
        addProductService.execute(request1);
        addProductService.execute(request2);
        addProductService.execute(request3);
        clearAllProductsService.execute();
        assertThat(productRepository.findAll().size()).isEqualTo(0);

    }
}
