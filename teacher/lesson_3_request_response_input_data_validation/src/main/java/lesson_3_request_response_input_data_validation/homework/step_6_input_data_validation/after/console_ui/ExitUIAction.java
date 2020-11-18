package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.console_ui;

public class ExitUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
