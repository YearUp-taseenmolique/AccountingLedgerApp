package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class reportManagement {
    private static final String dataFileName = "transactions.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayReports() {
        while (true) {
            System.out.println("\n----- Reports -----");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.println("Please Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    filterByDate(getCurrentMonthStart(), new Date());
                    break;
                case "2":
                    filterByDate(getPreviousMonthStart(), getPreviousMonthEnd());
                    break;
                case "3":
                    filterByDate(getCurrentYearStart(), new Date());
                    break;
                case "4":
                    filterByDate(getPreviousYearStart(), getPreviousYearEnd());
                case "5":
                    System.out.println("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    filterByVendor(vendor);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");

            }


        }
    }

    private static void filterByDate(Date startDate, Date endDate) {
        System.out.println("\n---- Filtered by Date ----");
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader BR = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = BR.readLine()) != null) {
                String[] tokens = line.split("\\|");
                Date transactionDate = SDF.parse(tokens[0]);
                if ((transactionDate.equals(startDate) || transactionDate.after(startDate)) &&
                        (transactionDate.equals(endDate) || transactionDate.before(endDate))) {
                    System.out.println(line.replace('|', ' '));
                }

            }

        } catch (Exception e) {
            System.out.println("Error reading ledger: " + e.getMessage());
        }

    }

    private static void filterByVendor(String vendor) {
        System.out.println("\n---- Filtered by Vendor: " + vendor + " ----");
        try (BufferedReader br = new BufferedReader(new FileReader(dataFileName))){
            String line;
            while ((line = br.readLine()) != null){
                String [] tokens = line.split("\\|");
                if (tokens[3].equalsIgnoreCase(vendor)){
                    System.out.println(line.replace('|', ' '));
                }
            }
        } catch (Exception e){
            System.out.println("Error reading ledger: "+ e.getMessage());
        }

    }

    private static Date getCurrentMonthStart() {
        return new Date();
    }

    private static Date getPreviousMonthStart() {
        return new Date();
    }

    private static Date getPreviousMonthEnd() {
        return new Date();
    }

    private static Date getCurrentYearStart() {
        return new Date();
    }

    private static Date getPreviousYearStart() {
        return new Date();
    }

    private static Date getPreviousYearEnd() {
        return new Date();
    }

}


