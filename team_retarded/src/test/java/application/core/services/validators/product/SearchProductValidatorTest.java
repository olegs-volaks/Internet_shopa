package application.core.services.validators.product;

import application.core.requests.product.Ordering;
import application.core.requests.product.Paging;
import application.core.requests.product.SearchProductRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchProductValidatorTest {

    private final SearchProductValidator subject = new SearchProductValidator();

    @Test
    void validateNoErrorAllDataIn() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering("name", "ASCENDING"), new Paging(2, 2)));
        assertThat(actual).isEmpty();
    }

    @Test
    void validateErrorEmptyBothNameAndDescription() {
        List<CoreError> actual = subject.validate(new SearchProductRequest(null, null,
                new Ordering("description", "ASCENDING"), new Paging(2, 2)));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("name",        "Must not be empty!"));
        expected.add(new CoreError("description", "Must not be empty!"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void validateErrorEmptyOrderDirection() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", null,
                new Ordering("name", null), new Paging(2, 2)));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("orderDirection") &&
                coreError.getMessage().equals("Must not be empty!"));
    }

    @Test
    void validateErrorEmptyOrderBy() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering(null, "ASCENDING"), new Paging(2, 2)));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("orderBy") &&
                coreError.getMessage().equals("Must not be empty!"));
    }

    @Test
    void validateEmptyOrderByAndOrderDirection() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering(null, null), new Paging(2, 2)));
        assertThat(actual).isEmpty();
    }

    @Test
    void validateErrorPageNumberNegative() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering("name", "ASCENDING"), new Paging(-2, 2)));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("pageNumber", "Must be greater then 0!"));
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void validateErrorPageSizeNegative() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering(null, null), new Paging(2, -2)));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("pageSize", "Must be greater then 0!"));
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void validateErrorPageNumberNull() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering("name", "ASCENDING"), new Paging(null, 2)));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("pageNumber", "Must not be empty!"));
        assertThat(actual).isNotEmpty();
        assertEquals(actual, expected);
    }

    @Test
    void validateErrorPageNumberAndSizeNull() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering("name", "ASCENDING"), new Paging(null, null)));
        assertThat(actual).isEmpty();
    }

    @Test
    void validateErrorPageSizeNull() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "Red1234567890",
                new Ordering(null, null), new Paging(2, null)));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("pageSize", "Must not be empty!"));
        assertThat(actual).isNotEmpty();
        assertEquals(actual, expected);
    }
}