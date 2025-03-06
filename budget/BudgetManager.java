package budget;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BudgetManager {
    private static final String MAIN_MENU = """
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            5) Save
            6) Load
            7) Analyze (Sort)
            0) Exit""";

    private final Map<String, Supplier<Command>> commandMap = new HashMap<>();

    {
        commandMap.put("1", AddIncomeCommand::new);
        commandMap.put("2", AddPurchaseCommand::new);
        commandMap.put("3", ShowListOfPurchasesCommand::new);
        commandMap.put("4", BalanceCommand::new);
        commandMap.put("5", SaveToFileCommand::new);
        commandMap.put("6", LoadFromFileCommand::new);
        commandMap.put("7", AnalyzeCommand::new);
        commandMap.put("0", ExitCommand::new);
    }

    public void run() {
        while (true) {
            IOHandler.print(MAIN_MENU);
            String input = IOHandler.nextLine();
            IOHandler.printEmptyLine();
            commandMap.getOrDefault(input, UnknownCommand::new).get().execute();
            IOHandler.printEmptyLine();

        }
    }
}
