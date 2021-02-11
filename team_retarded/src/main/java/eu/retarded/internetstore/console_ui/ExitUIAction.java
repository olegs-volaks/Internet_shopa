package eu.retarded.internetstore.console_ui;


import org.springframework.stereotype.Component;


@Component
public class ExitUIAction implements MenuUIAction {

    @Override
    public void execute() {
        System.out.println("Good Bye! ");
        System.exit(0);
    }
}
