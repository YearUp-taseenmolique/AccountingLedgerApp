package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ledgerManagement {
    private static final String dateFileName = "transactions.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public void displayLedger() {
        while (true) {
            System.out.println("\n---- Ledger ----");
            System.out.println("A) All");
            System.out.println("D) Deposit");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("Please select an option: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportManagement.displayReports();
                    break;
                case "H":
                    System.out.println("Returning to home screen");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
            }

        }
    }

    private static void displayAllTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader(dateFileName))){
            String line;
            System.out.println("\n----- All Transactions -----");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading transaction: " + e.getMessage());
        }
    }

    private static void displayDeposits() {
        try (BufferedReader br = new BufferedReader(new FileReader(dateFileName))){
            String line;
            boolean hasDeposits = false;
            System.out.println("\n----- Deposit(s) -----");

            while ((line = br.readLine()) != null){
                String[] tokens = line.split("\\|");
                String type = tokens[2];

                if (type.equalsIgnoreCase("Deposit")){
                    System.out.println(line);
                    hasDeposits = true;
                }

            }
            if (!hasDeposits) {
                System.out.println("No deposits found");
            }
        } catch (Exception e) {
            System.out.println("There was an error reading the transactions: " + e.getMessage());
        }
    }

    private static void displayPayments() {
        try (BufferedReader br = new BufferedReader(new FileReader(dateFileName))) {
         String line;
         boolean hasPayment = false;
         System.out.println("\n----- Payment(s) -----");

         while ((line = br.readLine()) != null) {
             String[] tokens = line.split("\\|");
             String type = tokens[2];

             if (type.equalsIgnoreCase("Payment")) {
                 System.out.println(line);
                 hasPayment = true;
             }
         }
         if (!hasPayment){
             System.out.println("No payments found");
         }

        }catch (Exception e){
            System.out.println("There was an error reading the transactions: " + e.getMessage());
        }
    }

    //Deposit Arg
    public void addDeposit() {

        //The date of the deposit
        System.out.print("Enter the date of your deposit (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        //The time of the deposit
        System.out.print("Enter the time of your deposit (hh:mm:ss): ");
        String time = scanner.nextLine();

        //Description of deposit
        System.out.print("Enter the description of your deposit: ");
        String description = scanner.nextLine();

        //The vendor name
        System.out.print("What is the vendor's name: ");
        String vendor = scanner.nextLine();

        //The deposit amount
        System.out.print("What is the deposit amount: ");
        String amount = scanner.nextLine();

        //Saving the payment
        String transaction = String.format("%s|%s|%s|%s|%s", date, time, description, vendor, amount);
            saveTransaction(transaction);
            System.out.println("The deposit has been recorded successfully!");


    }

    //Payment arg
    public void makePayment() {

        //The date of the payment
        System.out.print("Enter the date of your payment (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        //The time of the payment
        System.out.print("Enter the time of your payment (hh:mm:ss): ");
        String time = scanner.nextLine();

        //Description of payment
        System.out.print("Enter the description of your payment: ");
        String description = scanner.nextLine();

        //The vendor name
        System.out.print("What is the vendor's name: ");
        String vendor = scanner.nextLine();

        //The payment amount
        System.out.print("What is the payment amount: ");
        String amount = scanner.nextLine();

        //Saving the payment
        String transaction = String.format("%s|%s|%s|%s|-%s", date, time, description, vendor, amount);
        saveTransaction(transaction);
        System.out.println("The payment has been recorded successfully!");


    }

    private void saveTransaction(String transaction){
        try (FileWriter FW = new FileWriter(dateFileName, true)){
            FW.write(transaction + "\n");
        } catch (Exception e){
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }




}
