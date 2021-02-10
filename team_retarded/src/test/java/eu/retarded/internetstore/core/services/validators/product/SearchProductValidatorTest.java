package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchProductValidatorTest {

    private final SearchProductValidator subject = new SearchProductValidator();

    @Test
    void validateNoErrorAllDataIn() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "ASC",1));
        assertThat(actual).isEmpty();
    }

    @Test
    void validateErrorEmptyBothNameAndDescription() {
        List<CoreError> actual = subject.validate(new SearchProductRequest(null, "ASC",1));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("KeyWord", "Must not be empty!"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void validateErrorEmptyOrderDirection() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", null,1));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("orderBy") &&
                coreError.getMessage().equals("Must not be empty!"));
    }

    @Test
    void validateErrorEmptyOrderBy() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "",1) );
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("orderBy") &&
                coreError.getMessage().equals("Must not be empty!"));
    }


    @Test
    void validateErrorPageNegative() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi", "ASC",-1));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("page", "Must be greater then 0!"));
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void validateErrorPageZero() {
        List<CoreError> actual = subject.validate(new SearchProductRequest("Audi","ASC",0));
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("page", "Must be greater then 0!"));
        assertThat(actual).isNotEmpty();
        assertThat(actual).isEqualTo(expected);
    }


}