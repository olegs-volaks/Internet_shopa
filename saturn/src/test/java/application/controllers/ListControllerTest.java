package application.controllers;

import application.items.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListControllerTest {

    private final ListController subject = new ListController();

    List <Product> products = new ArrayList<>();

    @Test
    void add1() {
        boolean actual=subject.add("BMW", "211", new BigDecimal("10000.0"));
        assertTrue(actual);
    }


    @Test
    void add() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testDelete1() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void testDelete2() {
    }

    @Test
    void deleteWithPrice() {
    }

    @Test
    void findById() {
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