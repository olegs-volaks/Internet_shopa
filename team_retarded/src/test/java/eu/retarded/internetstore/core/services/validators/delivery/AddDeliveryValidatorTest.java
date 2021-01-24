package eu.retarded.internetstore.core.services.validators.delivery;

import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class AddDeliveryValidatorTest {

    private AddDeliveryValidator subject;

    @BeforeEach
    void setSubject() {
        subject = new AddDeliveryValidator();
    }

    @Test
    void validate_title_is_less_than_3_characters() {
        AddDeliveryRequest request = new AddDeliveryRequest("Ma", "description", 222.34);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Title") &&
                coreError.getMessage().equals("Must be between 3 and 100 characters"));
    }

    @Test
    void validate_title_is_more_than_3_characters() {
        AddDeliveryRequest request = new AddDeliveryRequest("Sni", "description", 222.34);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }

    @Test
    void validate_title_is_more_than_100_characters() {
        AddDeliveryRequest request = new AddDeliveryRequest("title111111111111111111111111111111111111111111111111111111" +
                "1111111111111111111111111111111111111111111111", "International", 224.67);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Title") &&
                coreError.getMessage().equals("Must be between 3 and 100 characters"));
    }

    @Test
    void validate_region_is_less_than_10_characters() {
        AddDeliveryRequest request = new AddDeliveryRequest("Roma", "Inter", 24567.33);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Region") &&
                coreError.getMessage().equals("Must be between 10 and 2000 characters"));
    }

    @Test
    void validate_region_is_more_than_10_characters() {
        AddDeliveryRequest request = new AddDeliveryRequest("Roma","Internacional",44.67);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }

    @Test
    void validate_region_is_empty() {
        AddDeliveryRequest request = new AddDeliveryRequest("Mars", "", 2222.22);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Region") &&
                coreError.getMessage().equals("Must not be empty"));
    }

    @Test
    void validate_price_is_null() {
        AddDeliveryRequest request = new AddDeliveryRequest("Nike","football ball",0);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Price") &&
                coreError.getMessage().equals("Must be between 0 and 1000000000"));
    }

    @Test
    void validate_price_is_fine() {
        AddDeliveryRequest request = new AddDeliveryRequest("Nike","football ball",100.00 );
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }

    @Test
    void valida_price_is_more_than_1000000000() {
        AddDeliveryRequest request = new AddDeliveryRequest("Nike", "football ball", 10000000000.00);
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Price") &&
                coreError.getMessage().equals("Must be between 0 and 1000000000"));
    }
}