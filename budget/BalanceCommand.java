package budget;

public class BalanceCommand implements Command {
    private static final String BALANCE = "BALANCE: $";

    @Override
    public void execute() {
        IOHandler.print(BALANCE + String.format("%.2f", Budget.getBalance()));
    }
}
