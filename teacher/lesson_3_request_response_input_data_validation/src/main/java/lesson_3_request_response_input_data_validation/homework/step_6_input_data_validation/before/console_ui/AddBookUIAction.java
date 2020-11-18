package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.console_ui;

import java.util.Scanner;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses.AddBookResponse;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.services.AddBookService;

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