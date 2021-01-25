package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryValidatorTest {

    @Mock
    CategoriesDatabase categoriesDatabase;

    @InjectMocks
    private DeleteCategoryValidator subject;

    @Test
    void should_by_successful() {
        long id = 7;
        Mockito.when(categoriesDatabase.isExist(id)).thenReturn(true);
        List<CoreError> result = subject.validate(new DeleteCategoryRequest(id));
        assertThat(result).isEmpty();
    }

    @Test
    void test_with_negative_id() {
        List<CoreError> result = subject.validate(new DeleteCategoryRequest(-7));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty or negative"));
    }

    @Test
    void test_with_0() {
        List<CoreError> result = subject.validate(new DeleteCategoryRequest(0));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty or negative"));
    }
}