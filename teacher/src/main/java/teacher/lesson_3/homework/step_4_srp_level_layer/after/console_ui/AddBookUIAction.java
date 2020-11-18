package teacher.lesson_3.homework.step_4_srp_level_layer.after.console_ui;

import java.util.Scanner;

import teacher.lesson_3.homework.step_4_srp_level_layer.after.services.AddBookService;

public class AddBookUIAction implements UIAction {

	private AddBookService addBookService;

	public AddBookUIAction(AddBookService addBookService) {
		this.addBookService = addBookService;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String bookTitle = scanner.nextLine();
		System.out.println("Enter book author: ");
		String bookAuthor = scanner.nextLine();
		addBookService.execute(bookTitle, bookAuthor);
		System.out.println("Your book was added to list.");
	}

}