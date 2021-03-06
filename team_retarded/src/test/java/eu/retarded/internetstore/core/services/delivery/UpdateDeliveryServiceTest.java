package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.UpdateDeliveryResponse;
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
class UpdateDeliveryServiceTest {

    @Mock private DeliveryRepository deliveryRepository;
    @Mock private Validator validator;
    @InjectMocks private UpdateDeliveryService subject;

    @Test
    void update_delivery_success() {
        UpdateDeliveryRequest request = new UpdateDeliveryRequest(1L,"Delivery","East",15000.0);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setTitle("Delivery");
        delivery.setRegion("East");
        delivery.setPrice(new BigDecimal("15000.0"));
        Delivery result = new Delivery();
        result.setId(1L);
        result.setTitle("Delivery");
        result.setRegion("East");
        result.setPrice(new BigDecimal("15000.0"));
        Mockito.when(deliveryRepository.getOne(1L)).thenReturn(delivery);
        UpdateDeliveryResponse response = subject.execute(request);
        Mockito.verify(deliveryRepository).getOne(request.getId());
        assertThat(response.getDeliveryId()).isEqualTo(result);
    }

}