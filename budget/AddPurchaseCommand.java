package budget;

public class AddPurchaseCommand implements Command {
    private static final String ENTER_NAME = "\nEnter purchase name:";
    private static final String ENTER_PRICE = "Enter its price:";
    private static final String CHOOSE_TYPE = """
            Choose the type of purchase
            1) Food
            2) Clothes
            3) Entertainment
            4) Other
            5) Back""";
    private static final String ADDED = "Purchase was added!\n";
    private static final String UNKNOWN = "Unknown action!";


    @Override
    public void execute() {
        boolean whileFlag = true;
        while (whileFlag) {
            IOHandler.print(CHOOSE_TYPE);
            Type type;
            String choice = IOHandler.nextLine();
            switch (choice) {
                case "1", "2", "3", "4":
                    type = Type.values()[Integer.parseInt(choice) - 1];
                    break;
                case "5":
                    whileFlag = false;
                    continue;
                default:
                    IOHandler.print(UNKNOWN);
                    continue;
            }

            IOHandler.print(ENTER_NAME);
            String purchaseName = IOHandler.nextLine();
            IOHandler.print(ENTER_PRICE);
            double purchasePrice = Double.parseDouble(IOHandler.nextLine());
            Budget.addPurchase(new Purchase(purchaseName, purchasePrice, type));
            IOHandler.print(ADDED);

        }


    }
}
