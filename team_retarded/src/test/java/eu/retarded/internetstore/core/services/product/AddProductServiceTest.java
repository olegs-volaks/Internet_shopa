package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.responses.product.AddProductResponse;
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
class AddProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private AddProductService subject;

    @Test
    void add_product_success() {
        AddProductRequest request = new AddProductRequest("Igor12345",
                "1234567890qwertyuiopasdfghjklzxcvbnm1234567890", 345.25,5);
       Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<AddProductRequest>>());
        Product product = new Product("Igor12345", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345.25,5);
        //product.setId(1L);
        product.setStatus(1);
        Product result = new Product();
        result.setName("Igor12345");
        result.setDescription("1234567890qwertyuiopasdfghjklzxcvbnm1234567890");
        result.setPrice(BigDecimal.valueOf(345.25));
        result.setCount(5);
        result.setId(1L);
        result.setStatus(1);
        Mockito.when(productRepository.save(product)).thenReturn(result);
        AddProductResponse addProductResponse = subject.execute(request);
        assertThat(addProductResponse.getProduct()).isEqualTo(result);
        Mockito.verify(productRepository).save(product);
    }

}