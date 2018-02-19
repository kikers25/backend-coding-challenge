package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.engage.expense.service.ExpenseService.PERCENTAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceTest {

    @InjectMocks
    private ExpenseService expenseService;
    @Mock
    private JdbcTemplateExpenseDao dao;

    public static final BigDecimal AMOUNT = new BigDecimal(10);
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Test
    public void should_calculate_vat_of_an_expense() {
        Expense expense = new Expense().setAmount(new BigDecimal("10"));

        Expense result = expenseService.add(expense);

        assertThat(result.getVat(), is(not(nullValue())));
    }

    @Test
    public void should_be_20_the_percentage_of_the_vat() {

        assertThat(PERCENTAGE, is(new BigDecimal(20)));
    }

    @Test
    public void should_generate_the_vat_which_is_part_of_the_amount() {
        checkVAT(AMOUNT, AMOUNT.multiply(PERCENTAGE).divide(ONE_HUNDRED));
        checkVAT(aNumber(10), aNumber(2));
        checkVAT(aNumber(15), aNumber(3));
        checkVAT(aNumber(100), aNumber(20));
    }

    @Test
    public void should_generate_the_vat_with_just_two_decimal_places() {
        Expense expense = new Expense().setAmount(new BigDecimal(27.94));

        Expense result = expenseService.add(expense);

        assertThat(result.getVat(), is(new BigDecimal("5.59")));
    }

    @Test
    public void should_generate_the_vat_which_is_part_of_the_amount_2() {
    }

    private void checkVAT(BigDecimal amount, BigDecimal expectedVAT) {
        Expense expense = new Expense().setAmount(amount);

        Expense result = expenseService.add(expense);

        assertThat(result.getVat(), is(expectedVAT));
    }

    private BigDecimal aNumber(int val) {
        return new BigDecimal(val);
    }

}