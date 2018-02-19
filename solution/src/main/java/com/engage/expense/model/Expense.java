package com.engage.expense.model;

import com.engage.expense.config.LocalDateDeserializer;
import com.engage.expense.config.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Expense {

    // dd/mm/yyyy
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(required = true,example = "01/01/2016")
    private LocalDate date;

    @ApiModelProperty(required = true,example = "15.57")
    private BigDecimal amount;

    @ApiModelProperty(example = "5.10")
    private BigDecimal vat;

    @ApiModelProperty(required = true,example = "A reason")
    private String reason;

    public LocalDate getDate() {
        return date;
    }

    public Expense setDate(LocalDate date) {
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
