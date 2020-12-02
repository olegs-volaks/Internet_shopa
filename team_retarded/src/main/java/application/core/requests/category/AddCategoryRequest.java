package application.core.requests.category;

public class AddCategoryRequest {
    private final String name;

    public String getName() {
        return name;
    }

    public AddCategoryRequest(String name) {
        this.name = name;
    }
}
