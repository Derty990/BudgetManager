package budget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ShowListOfPurchasesCommand implements Command {
    private static final String EMPTY_LIST = "The purchase list is empty";
    private static final String TOTAL_SUM = "Total sum: $";
    private static final String CHOOSE_TYPE = """
            Choose the type of purchases
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) All
            6) Back""";

    private final Predicate<Purchase> foodPredicate = p -> p.type().equals(Type.Food);
    private final Predicate<Purchase> clothesPredicate = p -> p.type().equals(Type.Clothes);
    private final Predicate<Purchase> entertainmentPredicate = p -> p.type().equals(Type.Entertainment);
    private final Predicate<Purchase> otherPredicate = p -> p.type().equals(Type.Other);
    private final Map<String, Predicate<Purchase>> typePredicates = new HashMap<>();

    {
        typePredicates.put("1", foodPredicate);
        typePredicates.put("2", clothesPredicate);
        typePredicates.put("3", entertainmentPredicate);
        typePredicates.put("4", otherPredicate);
        typePredicates.put("5", p -> true);
    }

    @Override
    public void execute() {
        boolean whileFlag = true;
        while (whileFlag) {

            IOHandler.print(CHOOSE_TYPE);
            String choice = IOHandler.nextLine();
            IOHandler.printEmptyLine();
            switch (choice) {
                case "1", "2", "3", "4", "5":
                    IOHandler.print(String.valueOf(Type.values()[Integer.parseInt(choice) - 1]));
                    List<Purchase> purchaseList = Budget.getListOfPurchases().stream().filter(typePredicates.get(choice)).toList();
                    if (purchaseList.isEmpty()) {
                        IOHandler.print(EMPTY_LIST);
                    } else {
                        purchaseList.forEach(purchase -> IOHandler.print(purchase.toString()));
                        IOHandler.print(TOTAL_SUM + String.format("%.2f", purchaseList.stream().mapToDouble(Purchase::price).sum()));
                        IOHandler.printEmptyLine();
                    }
                    break;
                case "6":
                    whileFlag = false;
                    break;
            }


        }

    }
}
