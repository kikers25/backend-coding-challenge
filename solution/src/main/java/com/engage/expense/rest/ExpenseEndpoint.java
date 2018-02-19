package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ExpenseEndpoint.class.getName());

    @Autowired
    public ExpenseEndpoint(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @GET
    @Produces("application/json")
    public List<Expense> getAll() {
        logger.debug("Calling getAll");

        return expenseService.getAll();
    }

    @POST
    @Consumes("application/json")
    public void addOne(Expense expense) {
        logger.debug("Calling add one: " + expense);
        check(expense);

        expenseService.add(expense);
    }

    private void check(Expense expense) {
        if (expense.getAmount() == null || expense.getDate() == null
                || expense.getReason() == null || "".equals(expense.getReason().trim())) {
            throw new IllegalArgumentException("Amount, date and reason are mandatory fields");
        }
        if(expense.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Amount should not have more than 2 decimals: " + expense.getAmount());
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
