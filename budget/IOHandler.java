package budget;

import java.util.Scanner;

public class IOHandler {
    private static final Scanner scanner = new Scanner(System.in);


    public static String nextLine() {
        return scanner.nextLine();
    }


    public static void print(String message) {
        System.out.println(message);
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
