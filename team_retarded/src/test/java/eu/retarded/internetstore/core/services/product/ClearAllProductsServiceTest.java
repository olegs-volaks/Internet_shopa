package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.ClearAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ClearAllProductsResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ClearAllProductsServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ClearAllProductsService subject;

    @Test
    void clear_all_product_success() {
        ClearAllProductsRequest request = new ClearAllProductsRequest();

        Product product1 = new Product("Igor12345", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,5);
        Product product2 = new Product("Igor12346", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                450,5);
        productRepository.save(product1);
        productRepository.save(product2);


        ClearAllProductsResponse clearAllProductsResponse = subject.execute();
        Mockito.verify(productRepository).deleteAll();
        assertThat(productRepository.findAll().size()).isEqualTo(0);

    }
}