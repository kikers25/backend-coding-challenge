package com.engage.expense.model;

public class Expense {
    private String date;
    private String amount;
    private String vat;
    private String reason;

    public String getDate() {
        return date;
    }

    public Expense setDate(String date) {
        this.date = date;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public Expense setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getVat() {
        return vat;
    }

    public Expense setVat(String vat) {
        this.vat = vat;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Expense setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
