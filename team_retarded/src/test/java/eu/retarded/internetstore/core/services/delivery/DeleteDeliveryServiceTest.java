package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.DeleteDeliveryResponse;
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
class DeleteDeliveryServiceTest {

    @Mock private DeliveryRepository deliveryRepository;
    @Mock private Validator validator;
    @InjectMocks private DeleteDeliveryService subject;


    @Test
    void delete_delivery_success() {
        DeleteDeliveryRequest request = new DeleteDeliveryRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setTitle("Title");
        delivery.setRegion("Region");
        delivery.setPrice(new BigDecimal("10000"));
        Delivery result = new Delivery();
        result.setId(1L);
        result.setTitle("Title");
        result.setRegion("Region");
        result.setPrice(new BigDecimal("10000"));
       Mockito.when(deliveryRepository.existsById(1L)).thenReturn(false);
        DeleteDeliveryResponse response = subject.execute(request);
        Mockito.verify(deliveryRepository).deleteById(1L);
        assertThat(response.isDeleted()).isTrue();

    }


}