package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Path("/expenses")
@Component
public class ExpenseEndpoint {


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
    @Produces("application/json")
    @Consumes("application/json")
    public void addOne(Expense expense) {

        System.out.println("Read: " + expense);
    }


}
