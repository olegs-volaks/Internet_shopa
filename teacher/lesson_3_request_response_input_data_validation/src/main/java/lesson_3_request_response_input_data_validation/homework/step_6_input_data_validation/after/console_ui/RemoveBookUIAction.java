package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.console_ui;

import java.util.Scanner;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.core.requests.RemoveBookRequest;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.core.responses.RemoveBookResponse;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.core.services.RemoveBookService;

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
		RemoveBookRequest request = new RemoveBookRequest(bookId);
		RemoveBookResponse response = removeBookService.execute(request);

		if (response.hasErrors()) {
			response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
		} else {
			if (response.isBookRemoved()) {
				System.out.println("Your book was removed from list.");
			} else {
				System.out.println("Your book not removed from list.");
			}
		}
	}
}