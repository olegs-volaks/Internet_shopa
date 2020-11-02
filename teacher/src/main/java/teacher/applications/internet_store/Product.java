package teacher.applications.internet_store;

import java.util.Objects;

public class Product {

	private String title;
	private String description;

	public Product(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product) o;
		return Objects.equals(title, product.title) &&
				Objects.equals(description, product.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description);
	}

	@Override
	public String toString() {
		return "Product{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
