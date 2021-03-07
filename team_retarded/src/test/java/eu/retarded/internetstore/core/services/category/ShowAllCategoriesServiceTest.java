package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.responses.category.ShowAllCategoriesResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ShowAllCategoriesServiceTest {

    @Mock private CategoryRepository categoryRepository;
    @Mock private Validator validator;
    @InjectMocks private ShowAllCategoriesService subject;

    @Test
    void show_all_categories_success() {
        ShowAllCategoriesRequest request = new ShowAllCategoriesRequest();
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Category category = new Category();
        category.setName("Furniture");
        category.setId(1L);
        Category result = new Category();
        result.setName("Furniture");
        result.setId(1L);
        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        ShowAllCategoriesResponse response = subject.execute(request);
        Mockito.verify(categoryRepository).findAll();
        assertThat(response.getCategoriesList().size()).isEqualTo(1);
    }
}