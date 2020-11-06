package teacher.lesson_3.homework.step_5_srp_request_response.after.console_ui;

import teacher.lesson_3.homework.step_5_srp_request_response.after.core.requests.GetAllBooksRequest;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses.GetAllBooksResponse;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.services.GetAllBooksService;

public class GetAllBooksUIAction implements UIAction {

	private GetAllBooksService getAllBooksService;

	public GetAllBooksUIAction(GetAllBooksService getAllBooksService) {
		this.getAllBooksService = getAllBooksService;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = getAllBooksService.execute(request);
		response.getBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}