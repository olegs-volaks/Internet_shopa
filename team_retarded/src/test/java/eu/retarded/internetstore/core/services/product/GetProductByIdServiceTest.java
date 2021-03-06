package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.product.GetProductByIdResponse;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GetProductByIdServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private GetProductByIdService subject;

    @Test
    void get_product_success() {
        GetProductByIdRequest request = new GetProductByIdRequest(3L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<GetProductByIdRequest>>());
        Product product = new Product("Igor12345", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345.25,5);
        product.setId(3L);
        product.setStatus(1);
        Product result = new Product();
        result.setName("Igor12345");
        result.setDescription("1234567890qwertyuiopasdfghjklzxcvbnm1234567890");
        result.setPrice(BigDecimal.valueOf(345.25));
        result.setCount(5);
        result.setId(3L);
        result.setStatus(1);
        Mockito.when(productRepository.findById(3L)).thenReturn(Optional.of(product));
        GetProductByIdResponse getProductByIdResponse = subject.execute(request);
        assertThat(getProductByIdResponse.getProduct()).isEqualTo(result);
        Mockito.verify(productRepository).findById(3L);
    }
}