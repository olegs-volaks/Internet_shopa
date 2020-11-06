package teacher.lesson_3.homework.step_5_srp_request_response.after.console_ui;

import java.util.Scanner;

import teacher.lesson_3.homework.step_5_srp_request_response.after.core.requests.AddBookRequest;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses.AddBookResponse;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.services.AddBookService;

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
		AddBookRequest request = new AddBookRequest(bookTitle, bookAuthor);
		AddBookResponse response = addBookService.execute(request);
		System.out.println("New book id was: " + response.getNewBook().getId());
		System.out.println("Your book was added to list.");
	}

}