package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import eu.retarded.internetstore.database.product.ListProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ListProductDatabaseTest {

    @Mock
    CategoriesDatabase categoriesDatabase;
    @InjectMocks
    private ListProductDatabase subject;


    @Test
    void delete_by_predicate() {
        subject.add(new Product("name", "d", 12.9));
        subject.add(new Product("name2", "d", 12.9));
        subject.delete(product -> product.getName().equals("name") || product.getName().equals("name2"));
        assertThat(subject.getList()).isEmpty();
    }

    @Test
    void add() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "Xpro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).anyMatch(product -> product.getId() == 4 &&
                product.getName().equals("Mazda") &&
                product.getDescription().equals("3.0") &&
                product.getPrice().compareTo(new BigDecimal("3500.0")) == 0);
    }

    @Test
    void delete_by_id() {
        subject.add(new Product("Audi", "R8", 9000));
        subject.add(new Product("Nokia", "5110", 100));
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "Xpro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        subject.delete(2L);
        subject.delete(10L);
        subject.delete(5L);
        subject.delete(8L);
        subject.delete(9L);
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).allMatch(product -> product.getId() == 1 || product.getId() == 3 ||
                product.getId() == 4 || product.getId() == 6 || product.getId() == 7);


    }

    @Test
    void delete_by_name() {
        subject.add(new Product("Nike", "shoes", 100.0));
        subject.add(new Product("Iphone", "ProMax", 500.0));
        subject.add(new Product("Nokia", "3310", 55.0));
        subject.delete(product -> product.getName().equals("Nokia") || product.getName().equals("Nike"));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).noneMatch(product -> product.getName().equals("Nokia") ||
                product.getName().equals("Nike"));

    }

    @Test
    void delete_by_description() {
        subject.add(new Product("Ford", "Mustang", 50000.0));
        subject.add(new Product("Mazda", "CRX", 15000.0));
        subject.add(new Product("BMW", "M3", 25000.0));
        subject.delete(product -> product.getDescription().equals("M3") || product.getDescription().equals("Mustang"));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).noneMatch(product -> product.getDescription().equals("M3") ||
                product.getDescription().equals("Mustang"));

    }

    @Test
    void delete_by_price() {
        subject.add(new Product("name", "d", 12.9));
        subject.add(new Product("name2", "d", 17.8));
        subject.delete(product -> product.getPrice().compareTo(new BigDecimal("12.9")) == 0 ||
                product.getPrice().compareTo(new BigDecimal("17.8")) == 0);
        assertThat(subject.getList()).isEmpty();
    }

    @Test
    void delete_by_price_range() {
        subject.add(new Product("Ball", "5size", 50.0));
        subject.add(new Product("Tennis ball", "tennis", 10.0));
        subject.add(new Product("Mars", "2pac", 34.0));
        subject.add(new Product("Honda", "typeR", 1234.0));
        subject.add(new Product("PS4", "PRO", 200.0));
        subject.add(new Product("football", "ball", 60.0));
        subject.add(new Product("volleyball", "ball", 35.0));
        subject.delete(product -> product.getPrice().compareTo(new BigDecimal("10.0")) > 0 &&
                product.getPrice().compareTo(new BigDecimal("70.0")) < 0);
        assertThat(subject.getList()).isNotEmpty();
        assertThat(subject.getList()).noneMatch(product -> product.getPrice().compareTo(new BigDecimal("10.0")) > 0
                && product.getPrice().compareTo(new BigDecimal("70.0")) < 0);


    }

    @Test
    void clear() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "pro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        subject.clear();
        assertThat(subject.getList()).isEmpty();

    }

    @Test
    void get_by_id() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "pro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        Optional<Product> result = subject.getById(1L);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).matches(product -> product.getId() == 1 || product.getId() == 3 ||
                product.getId() == 5 || product.getId() == 6);

    }

    @Test
    void filter_by_name() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "pro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        List<Product> actual = subject.filter(product -> product.getName().equals("BMW") || product.getName().equals("PS4"));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(actual).allMatch(product -> product.getName().equals("BMW") || product.getName().equals("PS4"));
    }

    @Test
    void filter_by_description() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "Pro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "323", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        List<Product> actual = subject.filter(product -> product.getDescription().equals("323") ||
                product.getDescription().equals("Pro"));
        assertThat(subject.getList()).isNotEmpty();
        assertThat(actual).allMatch(product -> product.getDescription().equals("323") ||
                product.getDescription().equals("Pro"));

    }

    @Test
    void filter_by_price() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 4000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 10000.0));
        subject.add(new Product("Iphone", "pro", 100.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        List<Product> actual = subject.filter(product -> product.getPrice().compareTo(new BigDecimal("100.0")) > 0 &&
                product.getPrice().compareTo(new BigDecimal("4000.0")) < 0);
        assertThat(subject.getList()).isNotEmpty();
        assertThat(actual).allMatch(product -> product.getPrice().compareTo(new BigDecimal("100.0")) > 0 &&
                product.getPrice().compareTo(new BigDecimal("4000.0")) < 0);


    }


    @Test
    void filter_by_price_range() {
        subject.add(new Product("BMW", "323", 10000.0));
        subject.add(new Product("Honda", "2.0", 5000.0));
        subject.add(new Product("Opel", "1.6", 4000.0));
        subject.add(new Product("Mazda", "3.0", 3500.0));
        subject.add(new Product("Iphone", "Pro", 1000.0));
        subject.add(new Product("PS4", "Pro", 350.0));
        subject.add(new Product("BeatsByDRE", "", 100.0));
        subject.add(new Product("AppleMac", "Pro", 555.0));
        List<Product> actual = subject.filter(product -> product.getPrice().compareTo(new BigDecimal("300")) > 0 &&
                product.getPrice().compareTo(new BigDecimal("1000")) < 0);
        assertThat(subject.getList()).isNotEmpty();
        assertThat(actual).allMatch(product -> product.getId() == 5 || product.getId() == 6 || product.getId() == 8);
    }
}

