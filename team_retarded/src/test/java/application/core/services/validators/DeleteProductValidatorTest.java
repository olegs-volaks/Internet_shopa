package application.core.services.validators;

import application.core.requests.DeleteProductRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

class DeleteProductValidatorTest {

    private final DeleteProductValidator subject = new DeleteProductValidator();

    @Test
    void test1() {
        List<CoreError> expected=new ArrayList<>();
        List<CoreError> actual=subject.validate(new DeleteProductRequest(5));

        assertEquals(expected, actual);
    }
    @Test
    void test2() {
        List<CoreError> expected=new ArrayList<>();
        expected.add(new CoreError("ID", "Must not be empty, negative or fractional"));
        List<CoreError> actual=subject.validate(new DeleteProductRequest(0));

        assertThat(expected.equals(actual));
    }
}