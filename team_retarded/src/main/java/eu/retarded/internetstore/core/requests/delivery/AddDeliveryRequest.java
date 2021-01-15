package eu.retarded.internetstore.core.requests.delivery;


public class AddDeliveryRequest {

    private final String title;
    private final String region;
    private final double price;


    public String getTitle() {
        return title;
    }

    public String getRegion() {
        return region;
    }

    public double getPrice() {
        return price;
    }

    public AddDeliveryRequest(String title, String region, double price) {
        this.title = title;
        this.region = region;
        this.price = price;
    }
}
