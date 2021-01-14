package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.DeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.DeliveryValidator;
import eu.retarded.internetstore.database.DeliveryDatabase;
import eu.retarded.internetstore.matchers.DeliveryMatcher;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;


@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

    @Mock private DeliveryDatabase deliveryDatabase;
    @Mock private DeliveryValidator validator;
    @InjectMocks private DeliveryService subject;

    @Test
    public void should_return_response_with_errors_when_validation_fails() {
        DeliveryRequest request = new DeliveryRequest("123", "region123", 55.5);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Title", "Must be between 4 and 10 characters"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeliveryResponse response = subject.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrors().size(), 1);
        Assertions.assertEquals(response.getErrors().get(0).getField(), "Title");
        Assertions.assertEquals(response.getErrors().get(0).getMessage(), "Must be between 4 and 10 characters");
        Mockito.verifyNoInteractions(deliveryDatabase);
        Mockito.verify(validator).validate(request);
    }

    @Test
    public void add_product_to_database() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        DeliveryRequest request = new DeliveryRequest("Title", "Region", 55.5);
        DeliveryResponse response = subject.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Mockito.verify(deliveryDatabase).add(argThat(new DeliveryMatcher("Title", "Region")));
    }
}