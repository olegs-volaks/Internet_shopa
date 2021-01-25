package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.config.ApplicationConfiguration;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.AddDeliveryService;
import eu.retarded.internetstore.core.services.delivery.DeleteDeliveryService;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class DeliveryIntegrationTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private DeliveryDatabase deliveryDatabase;


    @BeforeEach
    void setUp() {
        deliveryDatabase.clear();
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
        assertThat(deliveryDatabase.getList().size()).isEqualTo(5);
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
        long id = addDeliveryService.execute(request).getDeliveryId();
        long id1 = addDeliveryService.execute(request1).getDeliveryId();
        long id2 = addDeliveryService.execute(request2).getDeliveryId();
        long id3 = addDeliveryService.execute(request3).getDeliveryId();
        long id4 = addDeliveryService.execute(request4).getDeliveryId();
        long id5 = addDeliveryService.execute(request5).getDeliveryId();
        deleteDeliveryService.execute(new DeleteDeliveryRequest(id));
        deleteDeliveryService.execute(new DeleteDeliveryRequest(id1));
        deleteDeliveryService.execute(new DeleteDeliveryRequest(id2));
        assertThat(deliveryDatabase.getList().size()).isEqualTo(3);
        assertThat(deliveryDatabase.isExist(id3)).isTrue();
        assertThat(deliveryDatabase.isExist(id4)).isTrue();
        assertThat(deliveryDatabase.isExist(id5)).isTrue();
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
        assertThat(deliveryDatabase.getList().size()).isEqualTo(3);
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
        assertThat(deliveryDatabase.getList().size()).isEqualTo(6);
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
}
