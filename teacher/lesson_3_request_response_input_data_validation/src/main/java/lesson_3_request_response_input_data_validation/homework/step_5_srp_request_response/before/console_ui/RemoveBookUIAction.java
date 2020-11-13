package lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.console_ui;

import java.util.Scanner;

import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.services.RemoveBookService;

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