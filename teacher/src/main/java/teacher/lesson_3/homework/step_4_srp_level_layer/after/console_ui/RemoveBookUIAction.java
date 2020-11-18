package teacher.lesson_3.homework.step_4_srp_level_layer.after.console_ui;

import java.util.Scanner;

import teacher.lesson_3.homework.step_4_srp_level_layer.after.Database;
import teacher.lesson_3.homework.step_4_srp_level_layer.after.services.RemoveBookService;

public class RemoveBookUIAction implements UIAction {

	private RemoveBookService removeBookService;

	public RemoveBookUIAction(RemoveBookService removeBookService) {
		this.removeBookService = removeBookService;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book id to remove: ");
		Long bookId = Long.parseLong(scanner.nextLine());
		removeBookService.execute(bookId);
		System.out.println("Your book was removed from list.");
	}
}