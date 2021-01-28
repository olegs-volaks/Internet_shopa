package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Delivery;

import eu.retarded.internetstore.database.delivery.ListDeliveryDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class ListDeliveryDatabaseTest {

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
    void clear() {
        subject.add(new Delivery("Volvo", "Kurzeme", 2500.0));
        subject.add(new Delivery("BMW", "Olaine", 7000.0));
        subject.add(new Delivery("Audi", "Liepaja", 15000.0));
        subject.add(new Delivery("Honda", "Daugavpils", 3000.0));
        subject.clear();
        assertThat(subject.getList()).isEmpty();
    }
}