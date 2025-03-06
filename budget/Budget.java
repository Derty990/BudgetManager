package budget;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private static double balance = 0;
    private static final List<Purchase> listOfPurchases = new ArrayList<>();

    public static void addBudget(double budget) {
        Budget.balance += budget;
    }

    public static double getBalance() {
        return balance;
    }

    public static void addPurchase(Purchase purchase) {
        balance -= purchase.price();
        listOfPurchases.add(purchase);
    }

    public static void loadPurchase(Purchase purchase) {
        listOfPurchases.add(purchase);
    }

    public static List<Purchase> getListOfPurchases() {
        return listOfPurchases;
    }


}
