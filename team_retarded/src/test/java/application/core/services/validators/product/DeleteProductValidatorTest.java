package application.core.services.validators.product;

import application.core.requests.product.DeleteProductRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteProductValidatorTest {

    private final DeleteProductValidator subject = new DeleteProductValidator();

    @Test
    void test1() {
        List<CoreError> actual = subject.validate(new DeleteProductRequest(5));
        assertThat(actual).isEmpty();
    }

    @Test
    void test2() {
        List<CoreError> actual = subject.validate(new DeleteProductRequest(0));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty, negative or fractional"));
    }
}