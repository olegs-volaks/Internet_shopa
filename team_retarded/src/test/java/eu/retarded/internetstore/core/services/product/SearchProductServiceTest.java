package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.product.SearchProductResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class SearchProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private SearchProductService subject;

    @Test
    void search_product_success() {
        Pageable pageable= PageRequest.of(2, 1);
        SearchProductRequest request = new SearchProductRequest("keyword",pageable);
        Mockito.when(validator.validate(request))
                .thenReturn(new HashSet<ConstraintViolation<SearchProductRequest>>());
        Product product1 = new Product("Igor12345keyword", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,5);
        Product product2 = new Product("Igor12345keyword", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,5);
        List<Product>resultList=new ArrayList<>();
        resultList.add(product1);
        resultList.add(product2);
        Page<Product> resultPage =new PageImpl<>(resultList);

        Mockito.when(productRepository.findByNameContaining("keyword",pageable)).thenReturn(resultPage);
        SearchProductResponse searchProductResponse = subject.execute(request);
        assertThat(searchProductResponse.getProductsList()).isEqualTo( resultList);
        assertThat(searchProductResponse.getProductsPage()).isEqualTo( resultPage);
        Mockito.verify(productRepository).findByNameContaining("keyword",pageable);
    }

}