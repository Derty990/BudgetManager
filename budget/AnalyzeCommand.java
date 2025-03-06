package budget;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnalyzeCommand implements Command {
    private static final String EMPTY_LIST = "The purchase list is empty";
    private static final String TOTAL_SUM = "Total sum: $";

    private static final String SORT_MENU = """
            How do you want to sort?
            1) Sort all purchases
            2) Sort by type
            3) Sort certain type
            4) Back""";
    private static final String CHOOSE_TYPE = """
            Choose the type of purchases
            1) Food
            2) Clothes
            3) Entertainment
            4) Other""";

    @Override
    public void execute() {
        while (true) {
            IOHandler.print(SORT_MENU);
            String choice = IOHandler.nextLine();

            List<Purchase> purchases = Budget.getListOfPurchases();
            switch (choice) {
                case "1":
                    IOHandler.printEmptyLine();
                    List<Purchase> sortedPurchaseList = new ArrayList<>(Budget.getListOfPurchases());
                    sortedPurchaseList.sort(Comparator.comparing(Purchase::price).reversed());
                    if (sortedPurchaseList.isEmpty()) {
                        IOHandler.print(EMPTY_LIST);
                    } else {
                        sortedPurchaseList.forEach(purchase -> IOHandler.print(purchase.toString()));
                        IOHandler.print(TOTAL_SUM + String.format("%.2f", sortedPurchaseList.stream().mapToDouble(Purchase::price).sum()));
                        IOHandler.printEmptyLine();
                    }
                    break;
                case "2":
                    IOHandler.printEmptyLine();
                    List<SumTypePurchase> typePurchaseList = new ArrayList<>();
                    typePurchaseList.add(new SumTypePurchase("Food", purchases.stream().filter(p -> p.type().equals(Type.Food)).mapToDouble(Purchase::price).sum()));
                    typePurchaseList.add(new SumTypePurchase("Entertainment", purchases.stream().filter(p -> p.type().equals(Type.Entertainment)).mapToDouble(Purchase::price).sum()));
                    typePurchaseList.add(new SumTypePurchase("Clothes", purchases.stream().filter(p -> p.type().equals(Type.Clothes)).mapToDouble(Purchase::price).sum()));
                    typePurchaseList.add(new SumTypePurchase("Other", purchases.stream().filter(p -> p.type().equals(Type.Other)).mapToDouble(Purchase::price).sum()));

                    typePurchaseList.sort(Comparator.comparing(SumTypePurchase::price).reversed());

                    IOHandler.print("Types:");
                    for (SumTypePurchase purchase : typePurchaseList) {
                        IOHandler.print(purchase.toString());
                    }
                    IOHandler.print("Total sum: $" + purchases.stream().mapToDouble(Purchase::price).sum());
                    break;
                case "3":
                    IOHandler.printEmptyLine();
                    IOHandler.print(CHOOSE_TYPE);
                    int chosenType = Integer.parseInt(IOHandler.nextLine()) - 1;
                    IOHandler.printEmptyLine();
                    List<Purchase> subList = new ArrayList<>(purchases.stream().filter(p -> p.type().equals(Type.values()[chosenType])).toList());
                    subList.sort(Comparator.comparing(Purchase::price).reversed());
                    IOHandler.print(Type.values()[chosenType].toString());
                    if (subList.isEmpty()) {
                        IOHandler.print(EMPTY_LIST);
                    } else {
                        subList.forEach(purchase -> IOHandler.print(purchase.toString()));
                        IOHandler.print(TOTAL_SUM + String.format("%.2f", subList.stream().mapToDouble(Purchase::price).sum()));
                    }

                    break;
                case "4":
                    return;
            }
            IOHandler.printEmptyLine();
        }

    }
}

record SumTypePurchase(String type, double price) {
    @Override
    public String toString() {
        return type + " - $" + String.format("%.2f", price);
    }
}
