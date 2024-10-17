package com.pluralsight;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ledgerManagement ledgerManager = new ledgerManagement();

    public static void main (String[] arg){
        while (true) {
            System.out.println("\n---- Accounting Ledger ----");
            System.out.println("D) Add Deposits");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Please select an option: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    ledgerManager.addDeposit();
                    break;
                case "P":
                    ledgerManager.makePayment();
                    break;
                case "L":
                    ledgerManager.displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting Application.");
                    return;
                default:
                    System.out.println("This is an invalid input. Please try again.");

            }
        }
    }

}
