package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.product.UpdateProductResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.HashSet;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private UpdateProductService subject;

    @Test
    void update_product_success() {
        UpdateProductRequest request = new UpdateProductRequest(10L, "audi",
                "1234567890qwertyuiopasdfghjklzxcvbnm1234567890", 345.0,5);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<UpdateProductRequest>>());
        Product product = new Product("ford", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,5);

        product.setStatus(1);
        Product result = new Product();
        result.setName("audi");
        result.setDescription("1234567890qwertyuiopasdfghjklzxcvbnm1234567890");
        result.setPrice(BigDecimal.valueOf(345.0));
        result.setCount(5);
        result.setId(10L);
        result.setStatus(1);
        Mockito.when(productRepository.getOne(10L)).thenReturn(product);
        UpdateProductResponse updateProductResponse = subject.execute(request);
        assertThat(updateProductResponse.getProduct()).isEqualTo(result);
    }

}