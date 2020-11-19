package application.core.requests;

public class FilterProductsByPriceRequest {

    private double priceMin;
    private double priceMax;

    public FilterProductsByPriceRequest(double priceMin, double priceMax) {
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

}
