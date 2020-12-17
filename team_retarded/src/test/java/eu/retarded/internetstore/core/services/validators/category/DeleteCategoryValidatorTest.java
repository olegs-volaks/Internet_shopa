package eu.retarded.internetstore.core.services.validators.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteCategoryValidatorTest {

    private DeleteCategoryValidator subject;

    @BeforeEach
    void setUp() {
        subject = new DeleteCategoryValidator();
    }

    @Test
    void should_by_successful() {
        List<CoreError> result = subject.validate(new DeleteCategoryRequest(7));
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