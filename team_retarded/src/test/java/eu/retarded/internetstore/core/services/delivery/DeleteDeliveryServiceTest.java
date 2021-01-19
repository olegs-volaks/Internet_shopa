package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.DeleteDeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.DeleteDeliveryValidator;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DeleteDeliveryServiceTest {

    @Mock private DeleteDeliveryValidator validator;
    @Mock private DeliveryDatabase deliveryDatabase;
    @InjectMocks private DeleteDeliveryService service;

    @Test
    public  void should_return_response_with_errors_when_validator_fails() {
        DeleteDeliveryRequest request = new DeleteDeliveryRequest(0);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID","Must not be empty,negative or fractional"));
        Mockito.when((validator.validate(request))).thenReturn(errors);
        DeleteDeliveryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"ID");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(deliveryDatabase);
    }
    @Test
    public void should_delete_delivery_with_id_from_database () {
        DeleteDeliveryRequest request = new DeleteDeliveryRequest(5L);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(deliveryDatabase.delete(5L)).thenReturn(false);
        DeleteDeliveryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertFalse(response.isDeliveryDeleted());

    }
}