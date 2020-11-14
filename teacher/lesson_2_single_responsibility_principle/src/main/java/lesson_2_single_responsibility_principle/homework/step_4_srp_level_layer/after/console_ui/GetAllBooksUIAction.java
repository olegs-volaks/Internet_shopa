package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.console_ui;

import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.services.GetAllBooksService;

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