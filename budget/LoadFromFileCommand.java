package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadFromFileCommand implements Command {

    private static final String FILE_LOADED = "Purchases were loaded!";

    double balance;
    List<String> readInfo = new ArrayList<>();

    @Override
    public void execute() {
        try (Scanner sc = new Scanner(new File("purchases.txt"))) {
            while (sc.hasNextLine()) {
                readInfo.add(sc.nextLine());
            }
            if (readInfo.isEmpty()) {
                IOHandler.print("File is empty");
            }
            balance = Double.parseDouble(readInfo.get(0));
            Budget.addBudget(balance);

            for (int i = 1; i < readInfo.size(); i += 3) {
                Budget.loadPurchase(new Purchase(readInfo.get(i),
                        Double.parseDouble(readInfo.get(i + 1)),
                        Type.valueOf(readInfo.get(i + 2))));
            }

            IOHandler.print(FILE_LOADED);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
