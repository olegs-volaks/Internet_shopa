package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.responses.category.UpdateCategoryResponse;
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
class UpdateCategoryServiceTest {

    @Mock private CategoryRepository categoryRepository;
    @Mock private Validator validator;
    @InjectMocks UpdateCategoryService subject;

    @Test
    void update_category_success() {
        UpdateCategoryRequest request = new UpdateCategoryRequest(1L,"Apple");
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Category category = new Category();
        category.setName("Apple");
        category.setId(1L);
        Category result = new Category();
        result.setName("Apple");
        result.setId(1L);
        Mockito.when(categoryRepository.getOne(1L)).thenReturn(category);
        UpdateCategoryResponse response = subject.execute(request);
        Mockito.verify(categoryRepository).getOne(request.getId());
        assertThat(response.getCategory()).isEqualTo(result);

    }

}