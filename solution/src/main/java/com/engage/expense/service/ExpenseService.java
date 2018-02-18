package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExpenseService {

    public List<Expense> getAll() {

        Expense expense = new Expense() //
                .setAmount("10.15") //
                .setDate("01/01/2015") //
                .setReason("dinner with lead") //
                .setVat("2.03");
        return Collections.singletonList(expense);
    }
}
