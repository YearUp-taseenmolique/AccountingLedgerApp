package com.pluralsight;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private String amount;

    public Transaction (String date, String time, String description, String vendor, String amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String toCSV() {
        return String.format("%s|%s|%s|%s|%s", date, time, description, vendor, amount);

    }

}
