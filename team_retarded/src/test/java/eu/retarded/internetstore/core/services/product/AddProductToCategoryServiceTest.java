package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.product.AddProductToCategoryResponse;
import eu.retarded.internetstore.database.CategoryRepository;
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
class AddProductToCategoryServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private AddProductToCategoryService subject;

    @Test
    void add_product_to_category_success() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1L, 1L);
        Mockito.when(validator.validate(request))
                .thenReturn(new HashSet<ConstraintViolation<AddProductToCategoryRequest>>());
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
        result.setCategory(category);
        Mockito.when(categoryRepository.getOne(1l)).thenReturn(category);
        Mockito.when(productRepository.getOne(1l)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(result);
        AddProductToCategoryResponse addProductToCategoryResponse = subject.execute(request);
        assertThat(addProductToCategoryResponse.productInCategory()).isEqualTo(true);
        Mockito.verify(productRepository).save(product);
    }
}