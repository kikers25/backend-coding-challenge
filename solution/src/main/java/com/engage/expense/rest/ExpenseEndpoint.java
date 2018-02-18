package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public List<Expense> get() {

        return expenseService.getAll();
    }


}
