<<<<<<< HEAD:teacher/lesson_2_single_responsibility_principle/src/main/java/lesson_2_single_responsibility_principle/homework/step_4_srp_level_layer/before/console_ui/ExitUIAction.java
package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.before.console_ui;
=======
package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.console_ui;
>>>>>>> origin/master:teacher/lesson_2_single_responsibility_principle/src/main/java/lesson_2_single_responsibility_principle/homework/step_4_srp_level_layer/after/console_ui/ExitUIAction.java

public class ExitUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
