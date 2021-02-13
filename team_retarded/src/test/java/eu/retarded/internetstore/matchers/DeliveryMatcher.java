package eu.retarded.internetstore.matchers;

import eu.retarded.internetstore.core.domain.Delivery;
import org.mockito.ArgumentMatcher;

public class DeliveryMatcher implements ArgumentMatcher<Delivery> {

    private final String title;
    private final String region;

    public DeliveryMatcher(String title, String region) {
        this.title = title;
        this.region = region;
    }

    @Override
    public boolean matches(Delivery argument) {
        return argument.getTitle().equals(title) &&
                argument.getRegion().equals(region);
    }
}
