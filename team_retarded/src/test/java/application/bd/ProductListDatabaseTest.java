package application.bd;

import application.items.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductListDatabaseTest {

    private final ProductListDatabase subject = new ProductListDatabase();

    @Test
    void delete_by_predicate() {
        ProductListDatabase subject = new ProductListDatabase();
        subject.add("name", "d", 12.9);
        subject.add("name2", "d", 12.9);
        subject.delete(product -> product.getName().equals("name") || product.getName().equals("name2"));
        assertThat(subject.getList()).isEmpty();
    }

    @Test
    void add() {
        subject.add("BMW", "323", 10000.0);
        subject.add("Honda", "2.0", 5000.0);
        subject.add("Opel", "1.6", 4000.0);
        subject.add("Mazda", "3.0", 3500.0);
        subject.add("Iphone", "Xpro", 1000.0);
        subject.add("PS4", "Pro", 350.0);
        subject.add("BeatsByDRE", "", 100.0);
        subject.add("AppleMac", "Pro", 555.0);
        assertThat(subject.getList()).contains(new Product(4, "Mazda", "3.0", 3500.0));
    }

    @Test
    void delete_by_id() {

    }

    @Test
    void delete_by_name() {

    }

    @Test
    void delete_by_description() {

    }

    @Test
    void delete_by_price() {
        ProductListDatabase subject = new ProductListDatabase();
        subject.add("name", "d", 12.9);
        subject.add("name2", "d", 17.8);
        subject.delete(product -> product.getPrice().compareTo(new BigDecimal("12.9")) == 0 || product.getPrice().compareTo(new BigDecimal("17.8")) == 0);
        assertThat(subject.getList()).isEmpty();
    }

    @Test
    void delete_by_price_range() {

    }

    @Test
    void clear() {
        subject.add("BMW", "323", 10000.0);
        subject.add("Honda", "2.0", 5000.0);
        subject.add("Opel", "1.6", 4000.0);
        subject.add("Mazda", "3.0", 3500.0);
        subject.add("Iphone", "Xpro", 1000.0);
        subject.add("PS4", "Pro", 350.0);
        subject.add("BeatsByDRE", "", 100.0);
        subject.add("AppleMac", "Pro", 555.0);
        subject.clear();
        assertThat(subject.getList()).isEmpty();
    }

    @Test
    void get_by_id() {

    }

    @Test
    void filter_by_name() {

    }

    @Test
    void filter_by_description() {

    }

    @Test
    void filter_by_price() {

    }

    @Test
    void filter_by_price_range() {
        subject.add("BMW", "323", 10000.0);
        subject.add("Honda", "2.0", 5000.0);
        subject.add("Opel", "1.6", 4000.0);
        subject.add("Mazda", "3.0", 3500.0);
        subject.add("Iphone", "Xpro", 1000.0);
        subject.add("PS4", "Pro", 350.0);
        subject.add("BeatsByDRE", "", 100.0);
        subject.add("AppleMac", "Pro", 555.0);
//        Price range from 300 to 1000
        List<Product> actual = subject.filter(product -> product.getPrice().compareTo(new BigDecimal("300")) > 0 &&
                product.getPrice().compareTo(new BigDecimal("1000")) < 0);
//        assert ONLY if all products in the actual List have id 5; 6; 8
        assertThat(actual).allMatch(product -> product.getId() == 5 || product.getId() == 6 || product.getId() == 8);
    }
}

