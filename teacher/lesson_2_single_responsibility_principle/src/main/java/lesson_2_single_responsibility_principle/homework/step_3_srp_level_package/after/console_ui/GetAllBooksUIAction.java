package lesson_2_single_responsibility_principle.homework.step_3_srp_level_package.after.console_ui;

import lesson_2_single_responsibility_principle.homework.step_3_srp_level_package.after.database.Database;

public class GetAllBooksUIAction implements UIAction {

	private Database database;

	public GetAllBooksUIAction(Database database) {
		this.database = database;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		database.getAllBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}