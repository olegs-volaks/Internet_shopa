package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;

import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.AddDeliveryValidator;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
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
public class AddDeliveryServiceTest {

    @Mock private DeliveryDatabase deliveryDatabase;
    @Mock private AddDeliveryValidator validator;
    @InjectMocks private AddDeliveryService subject;

    @Test
    public void should_return_response_with_errors_when_validation_fails() {
        AddDeliveryRequest request = new AddDeliveryRequest("123", "region123", 55.5);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Title", "Must be between 4 and 10 characters"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddDeliveryResponse response = subject.execute(request);
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
        AddDeliveryRequest request = new AddDeliveryRequest("Title", "Region", 55.5);
        AddDeliveryResponse response = subject.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Mockito.verify(deliveryDatabase).add(argThat(new DeliveryMatcher("Title", "Region")));
    }
}