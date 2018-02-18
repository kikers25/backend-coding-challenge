package com.engage.expense.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Expense {

    // dd/mm/yyyy
    private String date;
    private BigDecimal amount;
    private BigDecimal vat;
    private String reason;

    public String getDate() {
        return date;
    }

    public Expense setDate(String date) {
        this.date = date;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Expense setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public Expense setVat(BigDecimal vat) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(date, expense.date) &&
                Objects.equals(amount, expense.amount) &&
                Objects.equals(vat, expense.vat) &&
                Objects.equals(reason, expense.reason);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, amount, vat, reason);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "date='" + date + '\'' +
                ", amount='" + amount + '\'' +
                ", vat='" + vat + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
