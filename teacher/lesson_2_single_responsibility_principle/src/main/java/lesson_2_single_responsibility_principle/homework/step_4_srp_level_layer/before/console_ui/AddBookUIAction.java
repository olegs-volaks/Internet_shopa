package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.before.console_ui;

import java.util.Scanner;

import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.before.Book;
import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.before.database.Database;

public class AddBookUIAction implements UIAction {

	private Database database;

	public AddBookUIAction(Database database) {
		this.database = database;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String bookTitle = scanner.nextLine();
		System.out.println("Enter book author: ");
		String bookAuthor = scanner.nextLine();
		Book book = new Book(bookTitle, bookAuthor);
		database.save(book);
		System.out.println("Your book was added to list.");
	}

}