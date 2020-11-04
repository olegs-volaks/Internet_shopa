package application.controllers;

import application.items.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ListControllerTest {

    private final ListController subject = new ListController();


    @Test
    void add() {
        boolean actual=subject.add("BMW", "211", 10000.0);
        assertTrue(actual);
    }

    @Test
    void delete() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530",20000.0);
        subject.add("BMW", "740",30000.0);
        boolean actual=subject.delete(2);
        assertTrue(actual);
    }

    @Test
    void testDelete() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.delete(5);
        assertFalse(actual);
    }

    @Test
    void testDelete1() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.delete(new Product(3,"BMW", "740", new BigDecimal("30000.0")));
        assertTrue(actual);
    }

    @Test
    void testDelete2() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.delete(new Product(5,"BMW", "740", new BigDecimal("30000.0")));
        assertFalse(actual);
    }

    @Test
    void testDelete3() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.delete(Predicate.isEqual(new Product(3,"BMW", "740", new BigDecimal("30000.0"))));
        assertTrue(actual);
    }

    @Test
    void deleteAll() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.deleteAll();
        assertTrue(actual);
    }

    @Test
    void testDelete4() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.delete("BMW");
        assertTrue(actual);
    }

    @Test
    void deleteWithPrice() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.deleteWithPrice(new BigDecimal("10000.0"));
        assertTrue(actual);
    }

    @Test
    void findById() {
        subject.add("BMW", "323", 10000.0);
        subject.add("BMW", "530", 20000.0);
        subject.add("BMW", "740", 30000.0);
        boolean actual=subject.deleteWithPrice(new BigDecimal("10000.0"));
        assertTrue(actual);
    }

    @Test
    void findByProductName() {
    }

    @Test
    void findByPrice() {
    }

    @Test
    void findByPredicate() {
    }
}