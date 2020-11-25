package application.core.services.validators;

import application.core.requests.GetProductByIdRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;



import static org.assertj.core.api.Assertions.assertThat;

class GetProductByIdValidatorTest {

    GetProductByIdValidator subject = new GetProductByIdValidator();

    @Test
    void validateId() {
        List<CoreError> result = subject.validate(new GetProductByIdRequest(1));
        assertThat(result).isEmpty();
    }

 /*   @Test
    void validateIdNegative() {
        List<CoreError> result = subject.validate(new GetProductByIdRequest(-1));
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("ID must be greater than 0 !"));
    }

 /*   @Test
    void validateIdZero() {
        List<CoreError> result = subject.validate(new GetProductByIdRequest(0));
        assertThat(result).isEmpty();
    }
*/
}