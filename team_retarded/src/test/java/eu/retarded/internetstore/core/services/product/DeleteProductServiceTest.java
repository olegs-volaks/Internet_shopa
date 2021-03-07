package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private DeleteProductService subject;

    @Test
    void delete_product_success() {
        DeleteProductRequest request = new DeleteProductRequest(2l);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<DeleteProductRequest>>());

        Mockito.when(productRepository.existsById(2l)).thenReturn(false);
        DeleteProductResponse deleteProductResponse = subject.execute(request);
        assertThat(deleteProductResponse.isDeleted()).isEqualTo(true);
        Mockito.verify(productRepository).deleteById(2l);
    }

}