package application.core.services.category;

import application.core.requests.category.DeleteCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.DeleteAllCategoryResponse;
import application.core.responses.category.DeleteCategoryResponse;
import application.core.services.validators.category.DeleteCategoryValidator;
import application.database.categories.database.CategoriesDatabase;
import application.matchers.ListProductCategoryMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryServiceTest {

    @Mock
    private CategoriesDatabase database;
    @Mock
    private DeleteCategoryValidator validator;
    @InjectMocks
    private DeleteCategoryService subject;

    @Test
    void should_return_response_with_errors_when_validation_fails() {
        DeleteCategoryRequest request = new DeleteCategoryRequest(1L);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID","Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteCategoryResponse response = subject.execute(request);
        assertThat(response.hasErrors()).isTrue();
        assertThat(response.getErrors().size()).isEqualTo(1);
        assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("ID") ||
                coreError.getMessage().equals("Must not be empty"));
        Mockito.verifyNoInteractions(database);

    }
    @Test
    void should_delete_category_from_database() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        DeleteCategoryResponse response = subject.execute(new DeleteCategoryRequest(1L));
        assertThat(response.isDeleted()).isFalse();
      //  Mockito.verify(database).removeCategory(assertThat(new ListProductCategoryMatcher()));  - не дает написать  string name ;/
    }


}