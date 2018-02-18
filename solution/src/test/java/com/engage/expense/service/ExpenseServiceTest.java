package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceTest {

    @InjectMocks
    private ExpenseService expenseService;
    @Mock
    private JdbcTemplateExpenseDao jdbcTemplateExpenseDao;

    @Test
    public void should_calculate_vat_of_an_expense() {
        Expense expense = new Expense().setAmount(new BigDecimal("10"));

        Expense result = expenseService.add(expense);

        assertThat(result.getVat(), is(not(nullValue())));
    }

}