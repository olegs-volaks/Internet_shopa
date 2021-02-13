package eu.retarded.internetstore.core.services.validators.delivery;


import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DeleteDeliveryValidatorTest {

    @Mock
    private DeliveryDatabase deliveryDatabase;

    @InjectMocks
    private DeleteDeliveryValidator subject;

    @Test
    void delete_delivery_by_id() {
        long id = 15677;
        Mockito.when(deliveryDatabase.isExist(id)).thenReturn(true);
        List<CoreError> result = subject.validate(new DeleteDeliveryRequest(id));
        assertThat(result).isEmpty();
    }

    @Test
    void delete_delivery_failed_or_negative() {
        long id = 0;
        List<CoreError> result = subject.validate(new DeleteDeliveryRequest(id));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty,negative or fractional"));
    }

    @Test
    void delivery_is_not_exist_in_database() {
        long id = 1111;
        Mockito.when(deliveryDatabase.isExist(id)).thenReturn(false);
        List<CoreError> result = subject.validate(new DeleteDeliveryRequest(id));
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("The delivery with the given id does not exist"));
    }
}