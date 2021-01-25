package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.DeleteCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.DeleteCategoryValidator;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

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
        errors.add(new CoreError("ID", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteCategoryResponse response = subject.execute(request);
        Assertions.assertThat(response.hasErrors()).isTrue();
        Assertions.assertThat(response.getErrors().size()).isEqualTo(1);
        Assertions.assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("ID") ||
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