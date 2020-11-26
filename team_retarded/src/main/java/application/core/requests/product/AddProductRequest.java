package application.core.requests.product;

public class AddProductRequest {

    private final String name;
    private final String description;
    private final double price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public AddProductRequest(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
