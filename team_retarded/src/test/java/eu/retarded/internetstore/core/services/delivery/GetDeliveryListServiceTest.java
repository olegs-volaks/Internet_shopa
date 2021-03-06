package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.delivery.GetDeliveryListResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(MockitoExtension.class)
class GetDeliveryListServiceTest {

    @Mock private DeliveryRepository deliveryRepository;
    @Mock private Validator validator;
    @InjectMocks GetDeliveryListService subject;

    @Test
    void get_delivery_list_success() {
        GetDeliveryListRequest request = new GetDeliveryListRequest();
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<>());
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setTitle("SIA Energy");
        delivery.setRegion("West side");
        delivery.setPrice(new BigDecimal("55000.0"));
        Delivery result = new Delivery();
        result.setId(1L);
        result.setTitle("SIA Energy");
        result.setRegion("West side");
        result.setPrice(new BigDecimal("55000.0"));
        Mockito.when(deliveryRepository.findAll()).thenReturn(Collections.singletonList(delivery));
        GetDeliveryListResponse response = subject.execute(request);
        Mockito.verify(deliveryRepository).findAll();
        assertThat(response.getDeliveriesList().size()).isEqualTo(1);
    }
}