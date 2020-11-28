package application.core.services.validators.product;

import application.core.requests.product.FilterProductsByNameRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilterProductsByNameValidatorTest {

    FilterProductsByNameValidator subject = new FilterProductsByNameValidator();

    @Test
    void validateName1() {
        List<CoreError> result = subject.validate(new FilterProductsByNameRequest("Alexander", "Most"));
        assertThat(result).isEmpty();
    }

    @Test
    void validateName2() {
        List<CoreError> result = subject.validate(new FilterProductsByNameRequest("Fil", "description"));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must be between 4 and 100 characters"));
    }

}