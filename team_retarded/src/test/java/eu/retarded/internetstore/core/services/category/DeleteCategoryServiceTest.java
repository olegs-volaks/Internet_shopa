package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteCategoryResponse;
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
class DeleteCategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private DeleteCategoryService subject;

    @Test
    void delete_category() {
        DeleteCategoryRequest request = new DeleteCategoryRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Category category = new Category();
        category.setName("Apple");
        category.setId(1L);
        Category result = new Category();
        result.setName("Apple");
        result.setId(1L);
        Mockito.when(categoryRepository.existsById(1L)).thenReturn(false);
        DeleteCategoryResponse response = subject.execute(request);
        Mockito.verify(categoryRepository).deleteById(1L);
        assertThat(response.isDeleted()).isTrue();

    }
}