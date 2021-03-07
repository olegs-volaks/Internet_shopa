package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductFromCategoryResponse;
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
class DeleteProductFromCategoryServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private DeleteProductFromCategoryService subject;

    @Test
    void delete_product_from_category_success() {
        DeleteProductFromCategoryRequest request = new DeleteProductFromCategoryRequest(1L);
        Mockito.when(validator.validate(request))
                .thenReturn(new HashSet<ConstraintViolation<DeleteProductFromCategoryRequest>>());
        Category category = new Category();
        category.setName("Cars");
        Product product = new Product("Igor12345", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,5);
        product.setCategory(category);
        product.setStatus(1);
        Product result = new Product();
        result.setName("Igor12345");
        result.setDescription("1234567890qwertyuiopasdfghjklzxcvbnm1234567890");
        result.setPrice(BigDecimal.valueOf(345));
        result.setCount(5);
        result.setId(1L);
        result.setStatus(1);
        result.setCategory(null);
        Mockito.when(productRepository.getOne(1l)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(result);
        DeleteProductFromCategoryResponse deleteProductFromCategoryResponse = subject.execute(request);
        assertThat(deleteProductFromCategoryResponse.productNotInCategory()).isEqualTo(true);
        Mockito.verify(productRepository).save(product);
        assertThat(productRepository.getOne(1l).getCategory()).isEqualTo(null);
    }
}