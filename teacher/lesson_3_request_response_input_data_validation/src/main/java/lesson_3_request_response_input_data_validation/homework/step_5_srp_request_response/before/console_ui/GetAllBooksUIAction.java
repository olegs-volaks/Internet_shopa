package lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.console_ui;

import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.services.GetAllBooksService;

public class GetAllBooksUIAction implements UIAction {

	private GetAllBooksService getAllBooksService;

	public GetAllBooksUIAction(GetAllBooksService getAllBooksService) {
		this.getAllBooksService = getAllBooksService;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		getAllBooksService.execute().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}