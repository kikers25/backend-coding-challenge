package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ExpenseService {

    public List<Expense> getAll() {

        Expense expense = new Expense() //
                .setAmount(new BigDecimal("10.15")) //
                .setDate(LocalDate.of(2015, 1, 1)) //
                .setReason("dinner with lead") //
                .setVat(new BigDecimal("2.03"));
        return Collections.singletonList(expense);
    }
}
