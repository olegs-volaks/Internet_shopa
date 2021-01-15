package eu.retarded.internetstore.core.services.validators.delivery;


import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteDeliveryValidatorTest {

    private final DeleteDeliveryValidator subject = new DeleteDeliveryValidator();

    @Test
    void delete_delivery_by_id() {
        List<CoreError> actual = subject.validate(new DeleteDeliveryRequest(3));
        assertThat(actual).isEmpty();
    }

    @Test
    void delete_delivery_failed_or_negative() {
        List<CoreError> actual = subject.validate(new DeleteDeliveryRequest(0));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty,negative or fractional"));
    }
}