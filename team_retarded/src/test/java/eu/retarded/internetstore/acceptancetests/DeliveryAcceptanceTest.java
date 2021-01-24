package eu.retarded.internetstore.acceptancetests;

import eu.retarded.internetstore.config.applicationConfiguration;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.services.delivery.AddDeliveryService;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryAcceptanceTest {

    private ApplicationContext context;
    private DeliveryDatabase deliveryDatabase;

    @BeforeEach
        void setUp() {
        context = new AnnotationConfigApplicationContext(applicationConfiguration.class);
        deliveryDatabase = context.getBean(DeliveryDatabase.class);
        deliveryDatabase.clear();
    }

    @Test
    void add_delivery_request() {
        AddDeliveryService service = context.getBean(AddDeliveryService.class);
        AddDeliveryRequest request = new AddDeliveryRequest("Iphone","Ilguciemsz",345.0);
        AddDeliveryRequest request1 = new AddDeliveryRequest("Samsung","Bolderaja rajons",800.0);
        AddDeliveryRequest request2 = new AddDeliveryRequest("Huawei","Vecmilgravis",349.0);
        AddDeliveryRequest request3 = new AddDeliveryRequest("Sonny","Zepniekalns",159.0);
        AddDeliveryRequest request4 = new AddDeliveryRequest("LG","Imanta",450.0);
        AddDeliveryRequest request5 = new AddDeliveryRequest("Audi","Mangalsala",50000.0);
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

    }

    @Test
    void show_all_delivery_request() {

    }

    @Test
    void get_delivery_by_id_request() {


    }

    @Test
    void search_delivery_request() {


    }

    @Test
    void add_delivery_validator_request() {


    }
}
