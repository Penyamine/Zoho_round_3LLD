package FindWife;

import java.util.Scanner;

public class ScannerObject {
    private static Scanner scanner=null;

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        ScannerObject.scanner = scanner;
    }
}
