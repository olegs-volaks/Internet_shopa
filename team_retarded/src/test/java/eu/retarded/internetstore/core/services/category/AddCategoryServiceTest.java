package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class AddCategoryServiceTest {

    @Mock private CategoryRepository categoryRepository;
    @Mock private Validator validator;
    @InjectMocks private AddCategoryService subject;

    @Test
    void add_category_success() {
        AddCategoryRequest request = new AddCategoryRequest("Igor");
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Category category = new Category();
        category.setName("Igor");
        Category result = new Category();
        result.setName("Igor");
        result.setId(1L);
        Mockito.when(categoryRepository.save(category)).thenReturn(result);
        AddCategoryResponse addCategoryResponse = subject.execute(request);
        assertThat(addCategoryResponse.getCategory()).isEqualTo(result);
        Mockito.verify(categoryRepository).save(category);
    }
}