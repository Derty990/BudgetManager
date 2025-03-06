package budget;

public class UnknownCommand implements Command {
    private static final String MESSAGE= "Unknown command";

    @Override
    public void execute() {
        IOHandler.print(MESSAGE);
    }
}
