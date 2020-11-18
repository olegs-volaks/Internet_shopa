package lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.console_ui;

public class ExitUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
