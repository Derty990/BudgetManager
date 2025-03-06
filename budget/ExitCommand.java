package budget;

public class ExitCommand implements Command {
    private static final String BYE = "Bye!";

    @Override
    public void execute() {
        IOHandler.print(BYE);
        System.exit(0);
    }
}
