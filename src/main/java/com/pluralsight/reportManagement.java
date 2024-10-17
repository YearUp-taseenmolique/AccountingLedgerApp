package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class reportManagement {
    private static final String dataFileName = "transactions.csv";
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                    filterByDate(getCurrentMonthStart(), LocalDate.now());
                    continue;
                case "2":
                    filterByDate(getPreviousMonthStart(), getPreviousMonthEnd());
                    continue;
                case "3":
                    filterByDate(getCurrentYearStart(), LocalDate.now());
                    continue;
                case "4":
                    filterByDate(getPreviousYearStart(), getPreviousYearEnd());
                    continue;
                case "5":
                    System.out.println("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    filterByVendor(vendor);
                    continue;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");

            }


        }
    }

    private static void filterByDate(LocalDate startdate, LocalDate endDate) {
        System.out.println("\n----- Filter by Date -----");
        try (BufferedReader br = new BufferedReader(new FileReader(dataFileName))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                LocalDate transactionDate = LocalDate.parse(tokens[0], formatter);
                if (!transactionDate.isBefore(startdate) && !transactionDate.isAfter(endDate)) {
                    System.out.println(line.replace('|', ' '));
                }
            }
        } catch (Exception e){
            System.out.println("Error reading ledger: " +e.getMessage());
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

    private static LocalDate getCurrentMonthStart() {
        return LocalDate.now().withDayOfMonth(1);
    }

    private static LocalDate getPreviousMonthStart() {
        return LocalDate.now().minusMonths(1).withDayOfMonth(1);
    }

    private static LocalDate getPreviousMonthEnd() {
        return LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth());
    }

    private static LocalDate getCurrentYearStart() {
        return LocalDate.now().withDayOfYear(1);
    }

    private static LocalDate getPreviousYearStart() {
        return LocalDate.now().minusYears(1).withDayOfYear(1);
    }

    private static LocalDate getPreviousYearEnd() {
        return LocalDate.now().minusYears(1).withDayOfYear(LocalDate.now().minusYears(1).lengthOfYear());
    }

}


