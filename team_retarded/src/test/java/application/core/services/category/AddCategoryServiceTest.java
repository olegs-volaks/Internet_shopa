package application.core.services.category;

import application.core.requests.category.AddCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.AddCategoryResponse;
import application.core.services.validators.category.AddCategoryValidator;
import application.database.categories.database.CategoriesDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AddCategoryServiceTest {

    @Mock
    private CategoriesDatabase database;
    @Mock
    private AddCategoryValidator validator;
    @InjectMocks
    private AddCategoryService subject;

    @Test
    void should_return_response_with_errors_when_validation_fails() {
        AddCategoryRequest request = new AddCategoryRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddCategoryResponse response = subject.execute(request);
        assertThat(response.hasErrors()).isTrue();
        assertThat(response.getErrors().size()).isEqualTo(1);
        assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("Name") ||
                coreError.getMessage().equals("Must not be empty!"));
        Mockito.verifyNoInteractions(database);
    }
}