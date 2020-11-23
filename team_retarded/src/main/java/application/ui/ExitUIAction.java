package application.ui;


public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good Bye! ");
        System.exit(0);
    }
}
