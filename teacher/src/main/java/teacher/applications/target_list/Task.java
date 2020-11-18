package teacher.applications.target_list;

import java.util.Objects;

public class Task {

	private String title;
	private String description;

	public Task(String title, String description) {
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
		Task task = (Task) o;
		return Objects.equals(title, task.title) &&
				Objects.equals(description, task.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, description);
	}

	@Override
	public String toString() {
		return "Task{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
