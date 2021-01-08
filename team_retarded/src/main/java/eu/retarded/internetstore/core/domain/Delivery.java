package eu.retarded.internetstore.core.domain;

public class Delivery {

    private String title;
    private int price;
    private String region;

    public Delivery(String title, int price, String region) {
        this.title = title;
        this.price = price;
        this.region = region;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", region='" + region + '\'' +
                '}';
    }


}
