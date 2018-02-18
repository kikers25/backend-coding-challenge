package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Path("/expenses")
@Component
public class ExpenseEndpoint {


    public static final BigDecimal ZERO = new BigDecimal(0);
    private ExpenseService expenseService;

    @Autowired
    public ExpenseEndpoint(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @GET
    @Produces("application/json")
    public List<Expense> getAll() {

        return expenseService.getAll();
    }

    @POST
    @Consumes("application/json")
    public void addOne(Expense expense) {
        check(expense);
        System.out.println("Read: " + expense);
    }

    private void check(Expense expense) {
        if (expense.getAmount() == null || expense.getDate() == null
                || expense.getReason() == null || "".equals(expense.getReason().trim())) {
            throw new IllegalArgumentException("Amount and date are mandatory fields");
        }
        LocalDate today = LocalDate.now();
        if (today.isBefore(expense.getDate())) {
            throw new IllegalArgumentException("Date " + expense.getDate() +" should be before than today: " + today);
        }
        if (ZERO.compareTo(expense.getAmount()) == 1 || ZERO.compareTo(expense.getAmount()) == 0) {
            throw new IllegalArgumentException("The amount should be a positive number: " + expense.getAmount());
        }
    }

}
