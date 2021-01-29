package eu.retarded.internetstore.core.services.validators.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetByIdDeliveryValidatorTest {

    private final GetByIdDeliveryValidator subject = new GetByIdDeliveryValidator();

    @Test
    void validate_id() {
        List<CoreError> result = subject.validate(new GetByIdDeliveryRequest(100L));
        assertThat(result).isEmpty();

    }

    @Test
    void validate_id_negative() {
        List<CoreError> result = subject.validate(new GetByIdDeliveryRequest(-10L));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must not be empty or negative"));

    }

    @Test
    void validate_id_is_zero() {
        List<CoreError> result = subject.validate(new GetByIdDeliveryRequest(0L));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getMessage().equals("Must not be empty or negative"));
    }
}