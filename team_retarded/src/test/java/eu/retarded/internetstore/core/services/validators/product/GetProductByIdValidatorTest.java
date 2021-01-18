package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetProductByIdValidatorTest {

    private final GetProductByIdValidator subject = new GetProductByIdValidator();

    @Test
    void validateId() {
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