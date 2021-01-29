package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
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
class GetCategoryByIdValidatorTest {

    @Mock
    private CategoriesDatabase categoriesDatabase;

    @InjectMocks
    private GetCategoryByIdValidator subject;

    @Test
    void validateId() {
        Mockito.when(categoriesDatabase.isExist(1L)).thenReturn(true);
        List<CoreError> result = subject.validate(new GetCategoryByIdRequest(1L));
        assertThat(result).isEmpty();
    }

    @Test
    void validateIdNegative() {
        List<CoreError> result = subject.validate(new GetCategoryByIdRequest(-1L));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must be more than 0"));
    }

    @Test
    void validateIdNotInBase() {
        Mockito.when(categoriesDatabase.isExist(1L)).thenReturn(false);
        List<CoreError> result = subject.validate(new GetCategoryByIdRequest(1L));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Category with this ID does not exist"));
    }


}