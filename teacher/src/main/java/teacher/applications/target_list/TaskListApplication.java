package teacher.applications.target_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskListApplication {

	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<>();

		while (true) {
			System.out.println("Program menu:");
			System.out.println("1. Add task to list");
			System.out.println("2. Delete task from list");
			System.out.println("3. Show all tasks in the list");
			System.out.println("4. Exit");

			System.out.println("");
			System.out.println("Enter menu item number to execute:");

			Scanner scanner = new Scanner(System.in);
			int userChoice = Integer.parseInt(scanner.nextLine());

			if (userChoice == 4) {
				System.out.println("Good by!");
				break;
			}

			switch (userChoice) {
				case 1: {
					System.out.println("Enter task title: ");
					String taskTitle = scanner.nextLine();
					System.out.println("Enter task description: ");
					String taskDescription = scanner.nextLine();
					Task task = new Task(taskTitle, taskDescription);
					tasks.add(task);
					System.out.println("Your task was added to list.");
					break;
				}
				case 2: {
					System.out.println("Enter task title: ");
					String taskTitle = scanner.nextLine();
					System.out.println("Enter task description: ");
					String taskDescription = scanner.nextLine();
					tasks.remove(new Task(taskTitle, taskDescription));
					System.out.println("Your task was removed from list.");
					break;
				}
				case 3: {
					System.out.println("Task list: ");
					for (Task book : tasks) {
						System.out.println(book);
					}
					System.out.println("Task list end.");
					break;
				}
			}
			System.out.println("");
		}

	}

}
