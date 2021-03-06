package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AddDeliveryServiceTest {

    @Mock private DeliveryRepository deliveryRepository;
    @Mock private Validator validator;
    @InjectMocks private AddDeliveryService subject;

    @Test
    void add_delivery_success() {
        AddDeliveryRequest request = new AddDeliveryRequest("SIA RG","Centre",4566.00);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Delivery delivery = new Delivery();
        delivery.setTitle("SIA RG");
        delivery.setRegion("Centre");
        delivery.setPrice(new BigDecimal("4566.00"));
        Delivery result = new Delivery();
        result.setTitle("SIA RG");
        result.setRegion("Centre");
        result.setPrice(new BigDecimal("4566.00"));
        Mockito.when(deliveryRepository.save(delivery)).thenReturn(delivery);
        AddDeliveryResponse response = subject.execute(request);
        Mockito.verify(deliveryRepository).save(new Delivery(request.getTitle(),request.getRegion(),request.getPrice()));
        assertThat(response.getDeliveryId()).isEqualTo(result);


    }

}