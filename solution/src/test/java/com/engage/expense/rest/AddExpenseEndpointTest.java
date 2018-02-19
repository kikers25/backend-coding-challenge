package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.AppException;
import com.engage.expense.service.ExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class AddExpenseEndpointTest {

    // Check that amount has just two decimals

    @InjectMocks
    private ExpenseEndpoint expenseEndpoint;

    @Mock
    private ExpenseService service;


    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_date_is_after_today() {
        Expense expense = getValidExpense();
        expense.setDate(LocalDate.now().plusDays(1));

        expenseEndpoint.addOne(expense);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_the_amount_is_negative() {
        Expense expense = getValidExpense();
        expense.setAmount(new BigDecimal(-5));

        expenseEndpoint.addOne(expense);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_the_amount_is_zero() {
        Expense expense = getValidExpense();
        expense.setAmount(new BigDecimal(0));

        expenseEndpoint.addOne(expense);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_amount_is_null() {
        Expense expense = getValidExpense();
        expense.setAmount(null);

        expenseEndpoint.addOne(expense);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_date_is_null() {
        Expense expense = getValidExpense();
        expense.setDate(null);

        expenseEndpoint.addOne(expense);
    }

    @Test
    public void should_throw_an_exception_when_reason_is_null_or_empty() {
        Expense expense = getValidExpense();
        expense.setDate(null);
        try {
            expenseEndpoint.addOne(expense);
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {}


        expense = getValidExpense();
        expense.setReason("");
        try {
            expenseEndpoint.addOne(expense);
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {}
    }

    @Test(expected = AppException.class)
    public void should_throw_an_exception_when_there_was_an_error_storing_the_expense() {
        doThrow(new AppException("error DB"))
                .when(service)
                .add(Mockito.any(Expense.class));

        expenseEndpoint.addOne(getValidExpense());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_an_exception_when_the_amount_has_more_than_2_decimals() {

        Expense validExpense = getValidExpense();
        validExpense.setAmount(new BigDecimal("1.137"));

        expenseEndpoint.addOne(validExpense);
    }

    private Expense getValidExpense() {
        return new Expense()
                .setDate(LocalDate.now().minusDays(5))
                .setAmount(new BigDecimal(15.5))
                .setReason("Reason");
    }
}