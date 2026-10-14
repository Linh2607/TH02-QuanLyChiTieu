package com.example.myapplication;
import java.io.Serializable;

public class Expense implements Serializable {
    private String title;
    private double amount;
    private String category;
    private String date;
    private String note;

    public Expense(String title, double amount, String category, String date, String note) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getNote() { return note; }
}