package application.controllers;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ListControllerTest {

    @Test
    void delete_by_predicate() {
        ListController subject = new ListController();
        subject.add("name", "d", 12.9);
        subject.add("name2", "d", 12.9);
        subject.delete(product -> product.getProductName().equals("name") || product.getProductName().equals("name2"));
        assertThat(subject.getList()).isEmpty();
    }
    @Test
    void delete_by_predicate_by_price() {
        ListController subject = new ListController();
        subject.add("name", "d", 12.9);
        subject.add("name2", "d", 17.8);
        subject.delete(product -> product.getPrice().compareTo(new BigDecimal("12.9")) == 0 || product.getPrice().compareTo(new BigDecimal("17.8")) == 0);
        assertThat(subject.getList()).isEmpty();
    }

}