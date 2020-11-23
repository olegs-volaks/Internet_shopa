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
        //assertThat(result).allMatch(coreError -> coreError.getField().equals("minimum price") &&
          //      coreError.getMessage().equals("Must not be empty or negative")) ;
    }


    @Test
    void validatePriceMax() {
        List<CoreError> result = subject.validate(new FilterProductsByPriceRequest(6,8));
        assertThat(result).isEmpty();
      //  assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must not be empty, negative or less than minimum price"));
    }

}