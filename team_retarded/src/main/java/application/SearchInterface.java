package main.java.application;

import java.util.List;

public interface SearchInterface {

    Product findById (long id);

    List<Product> findByProductName (String productName);

    List<Product> findByPrice (double price);

    List<Product> findByPriceLower (double price); // найти продукты с ценой ниже чем

    List<Product> findByPriceHigher (double price);

    List<Product> findBySpecificationContext (String context); // найти продукты, в описании которых есть фраза context




}
