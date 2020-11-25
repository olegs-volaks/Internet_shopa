package application.core.services.validators;

import application.core.requests.FilterProductsByPriceRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;



import static org.assertj.core.api.Assertions.assertThat;

class FilterProductsByPriceValidatorTest {

    FilterProductsByPriceValidator subject = new FilterProductsByPriceValidator();

    @Test
    void validatePriceMin() {
        List<CoreError> result = subject.validate(new FilterProductsByPriceRequest(1,5));
        assertThat(result).isEmpty();
    }


    @Test
    void validatePriceMax() {
        List<CoreError> result = subject.validate(new FilterProductsByPriceRequest(6,8));
        assertThat(result).isEmpty();
    }

    @Test
    void validatePriceMax1() {
        List<CoreError> result = subject.validate(new FilterProductsByPriceRequest(15,5));
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must not be empty, negative or less than minimum price"));
    }

    @Test
    void validatePriceNegative() {
        List<CoreError> result = subject.validate(new FilterProductsByPriceRequest(6,-5));
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must not be empty, negative or less than minimum price"));
    }

}