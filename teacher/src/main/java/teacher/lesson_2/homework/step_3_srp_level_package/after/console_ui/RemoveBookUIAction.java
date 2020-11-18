package teacher.lesson_2.homework.step_3_srp_level_package.after.console_ui;

import teacher.lesson_2.homework.step_3_srp_level_package.after.Database;

import java.util.Scanner;

public class RemoveBookUIAction implements UIAction {

	private Database database;

	public RemoveBookUIAction(Database database) {
		this.database = database;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book id to remove: ");
		Long bookId = Long.parseLong(scanner.nextLine());
		database.deleteById(bookId);
		System.out.println("Your book was removed from list.");
	}
}