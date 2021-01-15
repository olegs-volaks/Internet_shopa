package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class ListDeliveryDatabaseTest {

   // private DeliveryDatabase deliveryDatabase;
    private ListDeliveryDatabase subject;

    @BeforeEach
    void setUp() {
        subject = new ListDeliveryDatabase();
}

    @Test
    void add() {
        subject.add(new Delivery("Volvo", "Kurzeme", 2500.0));
        subject.add(new Delivery("BMW", "Olaine", 7000.0));
        subject.add(new Delivery("Audi", "Liepaja", 15000.0));
        subject.add(new Delivery("Honda", "Daugavpils", 3000.0));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).anyMatch(delivery -> delivery.getTitle().equals("Audi") &&
                delivery.getRegion().equals("Liepaja") &&
                delivery.getPrice().compareTo(new BigDecimal("15000")) == 0);
    }

    @Test
    void delete_by_title() {
        subject.add(new Delivery("Volvo", "Kurzeme", 2500.0));
        subject.add(new Delivery("BMW", "Olaine", 7000.0));
        subject.add(new Delivery("Audi", "Liepaja", 15000.0));
        subject.add(new Delivery("Honda", "Daugavpils", 3000.0));
        subject.delete(delivery -> delivery.getTitle().equals("Honda"));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).noneMatch(delivery -> delivery.getTitle().equals("Honda")); // тут ошибка

    }

    @Test
    void delete_by_price() {
        subject.add(new Delivery("Volvo", "Kurzeme", 2500.0));
        subject.add(new Delivery("BMW", "Olaine", 7000.0));
        subject.add(new Delivery("Audi", "Liepaja", 15000.0));
        subject.add(new Delivery("Honda", "Daugavpils", 3000.0));
        subject.delete(delivery -> delivery.getPrice().compareTo(new BigDecimal("2500.0")) == 0);
        assertThat(subject.getList()).isNotEmpty();
    }

    @Test
    void delete_by_region() {
        subject.add(new Delivery("Volvo", "Kurzeme", 2500.0));
        subject.add(new Delivery("BMW", "Olaine", 7000.0));
        subject.add(new Delivery("Audi", "Liepaja", 15000.0));
        subject.add(new Delivery("Honda", "Daugavpils", 3000.0));
        subject.delete(delivery -> delivery.getRegion().equals("Kurzeme") || delivery.getRegion().equals("Olaine"));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).noneMatch(delivery -> delivery.getRegion().equals("Kurzeme")  // !!!
                || delivery.getRegion().equals("Olaine"));


    }

    @Test
    void clear() {
        subject.add(new Delivery("Volvo", "Kurzeme", 2500.0));
        subject.add(new Delivery("BMW", "Olaine", 7000.0));
        subject.add(new Delivery("Audi", "Liepaja", 15000.0));
        subject.add(new Delivery("Honda", "Daugavpils", 3000.0));
        subject.clear();
        assertThat(subject.getList()).isEmpty();

    }

}