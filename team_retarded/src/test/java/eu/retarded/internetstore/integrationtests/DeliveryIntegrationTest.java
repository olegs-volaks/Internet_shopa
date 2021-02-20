package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.AddDeliveryService;
import eu.retarded.internetstore.core.services.delivery.DeleteDeliveryService;
import eu.retarded.internetstore.core.services.delivery.UpdateDeliveryService;
import eu.retarded.internetstore.database.DeliveryRepository;
import eu.retarded.internetstore.database.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DeliveryIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;


    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        deliveryRepository.deleteAll();
    }

    @Test
    void add_delivery_request() {
        AddDeliveryService service = context.getBean(AddDeliveryService.class);
        AddDeliveryRequest request = new AddDeliveryRequest("Iphone", "Ilguciemsz", 345.0);
        AddDeliveryRequest request1 = new AddDeliveryRequest("Samsung", "Bolderaja rajons", 800.0);
        AddDeliveryRequest request2 = new AddDeliveryRequest("Huawei", "Vecmilgravis", 349.0);
        AddDeliveryRequest request3 = new AddDeliveryRequest("Sonny", "Zepniekalns", 159.0);
        AddDeliveryRequest request4 = new AddDeliveryRequest("LG", "Imanta", 450.0);
        AddDeliveryRequest request5 = new AddDeliveryRequest("Audi", "Mangalsala", 50000.0);
        service.execute(request);
        service.execute(request1);
        service.execute(request2);
        service.execute(request3);
        service.execute(request4);
        service.execute(request5);
        assertThat(deliveryRepository.findAll().size()).isEqualTo(5); // deliveryDatabase.getList().size()).isEqualTo(5)
    }

    @Test
    void delete_delivery_request() {
        AddDeliveryService addDeliveryService = context.getBean(AddDeliveryService.class);
        DeleteDeliveryService deleteDeliveryService = context.getBean(DeleteDeliveryService.class);
        AddDeliveryRequest request = new AddDeliveryRequest("Iphone", "Ilguciemsz", 345.0);
        AddDeliveryRequest request1 = new AddDeliveryRequest("Samsung", "Bolderaja rajons", 800.0);
        AddDeliveryRequest request2 = new AddDeliveryRequest("Huawei", "Vecmilgravis2323", 349.0);
        AddDeliveryRequest request3 = new AddDeliveryRequest("Sonny", "Zepniekalns", 159.0);
        AddDeliveryRequest request4 = new AddDeliveryRequest("LG43", "Imanta1212112", 450.0);
        AddDeliveryRequest request5 = new AddDeliveryRequest("Audi", "Mangalsala121212", 50000.0);
        Delivery id = addDeliveryService.execute(request).getDeliveryId(); // long id
        Delivery id1 = addDeliveryService.execute(request1).getDeliveryId();
        Delivery id2 = addDeliveryService.execute(request2).getDeliveryId();
        Delivery id3 = addDeliveryService.execute(request3).getDeliveryId();
        Delivery id4 = addDeliveryService.execute(request4).getDeliveryId();
        Delivery id5 = addDeliveryService.execute(request5).getDeliveryId();
        deleteDeliveryService.execute(new DeleteDeliveryRequest(id.getId())); // deleteDeliveryService.execute(new DeleteDeliveryRequest(id())
        deleteDeliveryService.execute(new DeleteDeliveryRequest(id1.getId()));
        deleteDeliveryService.execute(new DeleteDeliveryRequest(id2.getId()));
        assertThat(deliveryRepository.findAll().size()).isEqualTo(3); // deliveryDatabase.getList().size()).isEqualTo(3)
        assertThat(deliveryRepository.existsById(id3.getId())).isTrue(); // deliveryDatabase.isExist(id3)).isTrue()
        assertThat(deliveryRepository.existsById(id4.getId())).isTrue(); // same
        assertThat(deliveryRepository.existsById(id5.getId())).isTrue(); // same
    }

    @Test
    void show_all_delivery_request() {
        AddDeliveryService addDeliveryService = context.getBean(AddDeliveryService.class);
        AddDeliveryRequest request = new AddDeliveryRequest("Apple", "Vecmilgravis", 467.0);
        AddDeliveryRequest request1 = new AddDeliveryRequest("LG222222", "Imanta22222222", 3200.0);
        AddDeliveryRequest request2 = new AddDeliveryRequest("Huawei", "Ziepniekalns", 355.0);
        addDeliveryService.execute(request);
        addDeliveryService.execute(request1);
        addDeliveryService.execute(request2);
        assertThat(deliveryRepository.findAll().size()).isEqualTo(3); // deliveryDatabase.getList().size()).isEqualTo(3)
    }

    @Test
    void get_delivery_by_id_request() {
        AddDeliveryService addDeliveryService = context.getBean(AddDeliveryService.class);
        AddDeliveryRequest request = new AddDeliveryRequest("Iphone", "Ilguciemsz", 345.0);
        AddDeliveryRequest request1 = new AddDeliveryRequest("Samsung", "Bolderaja rajons", 800.0);
        AddDeliveryRequest request2 = new AddDeliveryRequest("Huawei", "Vecmilgravis", 349.0);
        AddDeliveryRequest request3 = new AddDeliveryRequest("Sonny", "Zepniekalns", 159.0);
        AddDeliveryRequest request4 = new AddDeliveryRequest("LG2222222222", "Imanta2222222222", 450.0);
        AddDeliveryRequest request5 = new AddDeliveryRequest("Audi", "Mangalsala", 50000.0);
        addDeliveryService.execute(request);
        addDeliveryService.execute(request1);
        addDeliveryService.execute(request2);
        addDeliveryService.execute(request3);
        addDeliveryService.execute(request4);
        addDeliveryService.execute(request5);
        assertThat(deliveryRepository.findAll().size()).isEqualTo(6); // deliveryDatabase.getList().size()).isEqualTo(6)
    }

    @Test
    void add_delivery_validator_request() {
        AddDeliveryService addDeliveryService = context.getBean(AddDeliveryService.class);
        AddDeliveryRequest request = new AddDeliveryRequest("Ip", "Ilguciemsz", 345.0);
        AddDeliveryRequest request1 = new AddDeliveryRequest("Samsung", "Bo", 800.0);
        AddDeliveryRequest request2 = new AddDeliveryRequest("Huawei", "Vecmilgravis", 0);
        AddDeliveryRequest request3 = new AddDeliveryRequest("Sonny", "Zepniekalns", 1000.0);
        AddDeliveryResponse response = addDeliveryService.execute(request);
        AddDeliveryResponse response1 = addDeliveryService.execute(request1);
        AddDeliveryResponse response2 = addDeliveryService.execute(request2);
        AddDeliveryResponse response3 = addDeliveryService.execute(request3);
        assertThat(response.hasErrors()).isTrue();
        assertThat(response1.hasErrors()).isTrue();
        assertThat(response2.hasErrors()).isTrue();
        assertThat(response3.hasErrors()).isFalse();
    }

    @Test
    void update_delivery() {
        AddDeliveryService deliveryService = context.getBean(AddDeliveryService.class);
        UpdateDeliveryService updateDeliveryService = context.getBean(UpdateDeliveryService.class);
        Delivery id = deliveryService.execute(new AddDeliveryRequest("APPLE", "region", 1890.00)).getDeliveryId();
        Delivery id2 = deliveryService.execute(new AddDeliveryRequest("APPLE23", "region23", 450.00)).getDeliveryId();
        updateDeliveryService.execute(new UpdateDeliveryRequest(id2.getId(), "APPLE45", "region45", 20.00)); // id or id.getId()
        Delivery result = deliveryRepository.findById(id2.getId()).get(); // deliveryDatabase.getById(id2).get()
        Delivery expecting = new Delivery();
        expecting.setId(id2.getId()); // id
        expecting.setTitle("APPLE45");
        expecting.setRegion("region45");
        expecting.setPrice(new BigDecimal("20.00"));
        assertThat(result).isEqualTo(expecting);
    }
}
