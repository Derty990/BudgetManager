package budget;

import java.io.IOException;
import java.io.PrintWriter;

public class SaveToFileCommand implements Command {
    private static final String PURCHASES_SAVED = "Purchases were saved!";

    @Override
    public void execute() {
        try (PrintWriter pw = new PrintWriter("purchases.txt")) {
            double balance = Budget.getBalance();
            pw.println(balance);
            for (Purchase p : Budget.getListOfPurchases()) {
                pw.println(p.name());
                pw.println(p.price());
                pw.println(p.type());
            }
            IOHandler.print(PURCHASES_SAVED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
