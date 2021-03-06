package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.category.GetCategoryByIdResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GetCategoryByIdServiceTest {

    @Mock private CategoryRepository categoryRepository;

    @Mock private Validator validator;

    @InjectMocks private GetCategoryByIdService subject;

    @Test
    void get_category_by_id_success() {
        GetCategoryByIdRequest request = new GetCategoryByIdRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Category category = new Category();
        category.setName("Phone");
        category.setId(1L);
        Category result = new Category();
        result.setName("Phone");
        result.setId(1L);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        GetCategoryByIdResponse response = subject.execute(request);
        Mockito.verify(categoryRepository).findById(request.getCategoryId());
        assertThat(response.getCategory()).isEqualTo(result);
    }
}