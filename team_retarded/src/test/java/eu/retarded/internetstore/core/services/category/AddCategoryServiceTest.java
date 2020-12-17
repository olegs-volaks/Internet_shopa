package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.AddCategoryValidator;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import eu.retarded.internetstore.matchers.ListProductCategoryMatcher;
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
import static org.mockito.ArgumentMatchers.argThat;

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

    @Test
    void should_add_category_to_database() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddCategoryResponse response = subject.execute(new AddCategoryRequest("name1"));
        assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).addCategory(argThat(new ListProductCategoryMatcher("name1")));
    }
}