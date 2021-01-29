package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GetProductByIdValidatorTest {

    @Mock
    private ProductDatabase productDatabase;

    @InjectMocks
    private GetProductByIdValidator subject;

    @Test
    void validateId() {
        Mockito.when(productDatabase.isExist(1L)).thenReturn(true);
        List<CoreError> result = subject.validate(new GetProductByIdRequest(1));
        assertThat(result).isEmpty();
    }

    @Test
    void validateIdNegative() {
        List<CoreError> result = subject.validate(new GetProductByIdRequest(-1));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must be more than 0"));
    }

    @Test
    void validateIdZero() {
        List<CoreError> result = subject.validate(new GetProductByIdRequest(0));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must be more than 0"));
    }

}