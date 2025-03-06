package budget;

public class AddIncomeCommand implements Command {
    private static final String ENTER_INCOME = "Enter income:";
    private static final String INCOME_ADDED = "Income was added!";

    @Override
    public void execute() {
        IOHandler.print(ENTER_INCOME);
        double income = Double.parseDouble(IOHandler.nextLine());
        IOHandler.print(INCOME_ADDED);
        Budget.addBudget(income);
    }
}
