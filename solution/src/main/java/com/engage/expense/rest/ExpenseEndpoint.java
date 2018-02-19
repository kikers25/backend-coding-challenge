package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.ExpenseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Expenses API", produces = "application/json")
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
    @ApiOperation(				//Swagger Annotation
            value = "Get all the expenses",
            response = Expense.class)
    @ApiResponses(value = {		//Swagger Annotation
            @ApiResponse(code = 200, message = "Resource found"),
            @ApiResponse(code = 500, message = "Invalid parameters")})
    public List<Expense> getAll() {
        logger.debug("Calling getAll");

        return expenseService.getAll();
    }

    @POST
    @Consumes("application/json")
    @ApiOperation(				//Swagger Annotation
            value = "Add an expense")
    @ApiResponses(value = {		//Swagger Annotation
            @ApiResponse(code = 204, message = "Added Resource"),
            @ApiResponse(code = 500, message = "Invalid parameters")})
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
