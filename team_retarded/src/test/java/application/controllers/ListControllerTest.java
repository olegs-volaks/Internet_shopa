package application.controllers;

import application.items.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListControllerTest {

    private final ListController subject = new ListController();

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

    @Test
    void add() {
        boolean actual = subject.add("BMW", "211", 10000.0);
        assertTrue(actual);
    }

    @Test
    void delete() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.delete(2);
        assertTrue(actual);
    }

    @Test
    void testDelete() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.delete(5);
        assertFalse(actual);
    }

    @Test
    void testDelete1() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.delete(new Product(3, "BMW", "740", new BigDecimal("30000.0")));
        assertTrue(actual);
    }

    @Test
    void testDelete2() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.delete(new Product(5, "BMW", "740", new BigDecimal("30000.0")));
        assertFalse(actual);
    }

    @Test
    void testDelete3() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.delete(Predicate.isEqual(new Product(3, "BMW", "740", new BigDecimal("30000.0"))));
        assertTrue(actual);
    }

    @Test
    void deleteAll() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.deleteAll();
        assertTrue(actual);
    }

    @Test
    void testDelete4() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.delete("BMW");
        assertTrue(actual);
    }

    @Test
    void deleteWithPrice() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.deleteWithPrice(new BigDecimal("10000.0"));
        assertTrue(actual);
    }

    @Test
    void findById() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual = subject.deleteWithPrice(new BigDecimal("10000.0"));
        assertTrue(actual);
    }

    @Test
    void findByProductName() {   // DG
        subject.add("BMW", "323", 10000.0);
        subject.add("Honda", "2.0", 5000.0);
        subject.add("Opel", "1.6", 4000.0);
        subject.add("Mazda", "3.0", 3500.0);
        subject.add("Iphone", "Xpro", 1000.0);
        subject.add("PS4", "Pro", 350.0);
        subject.add("BeatsByDRE", "", 100.0);
        subject.add("AppleMac", "Pro", 555.0);
        List<Product> actual = subject.findByProductName("BMW");
        assertThat(actual);


    }

    @Test
    void findByPrice() { // DG
        subject.add("BMW", "323", 10000.0);
        subject.add("Honda", "2.0", 5000.0);
        subject.add("Mazda", "3.0", 3500.0);
        subject.add("Iphone", "Xpro", 1000.0);
        subject.add("PS4", "Pro", 350.0);
        subject.add("BeatsByDRE", "", 100.0);
        subject.add("AppleMac", "Pro", 555.0);
        List<Product> actual = subject.findByPrice(new BigDecimal("250.0"));
        assertThat(actual);
    }

    @Test
    void findByPredicate() { // DG не уверен что правильно
        subject.add("Honda", "2.0", 5000.0);
        subject.add("Mazda", "3.0", 3500.0);
        subject.add("Opel", "1.6", 4000.0);
        subject.add("Iphone", "Xpro", 1000.0);
        subject.add("PS4", "Pro", 350.0);
        subject.add("BeatsByDRE", "", 100.0);
        subject.add("AppleMac", "Pro", 555.0);
        List<Product> actual = subject.findByPredicate(Predicate.isEqual("Mazda"));
        assertThat(actual);
    }

    @Test
    void getList() { // DG
        List<Product> actual = subject.getList();
        assertThat(actual);
    }
}

