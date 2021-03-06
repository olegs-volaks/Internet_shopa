package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.GetByIdDeliveryResponse;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(MockitoExtension.class)
class GetByIdDeliveryServiceTest {

    @Mock private DeliveryRepository deliveryRepository;
    @Mock private Validator validator;
    @InjectMocks private GetByIdDeliveryService subject;

    @Test
    void get_by_id_delivery_success() {
        GetByIdDeliveryRequest request = new GetByIdDeliveryRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setTitle("SIA RG");
        delivery.setRegion("North");
        delivery.setPrice(new BigDecimal("100"));
        Delivery result = new Delivery();
        result.setId(1L);
        result.setTitle("SIA RG");
        result.setRegion("North");
        result.setPrice(new BigDecimal("100"));
        Mockito.when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));
        GetByIdDeliveryResponse response = subject.execute(request);
        Mockito.verify(deliveryRepository).findById(request.getId());
        assertThat(response.getDelivery()).isEqualTo(result);

    }

}