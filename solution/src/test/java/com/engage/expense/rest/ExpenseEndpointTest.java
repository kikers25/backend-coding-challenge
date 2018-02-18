package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import com.engage.expense.service.ExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseEndpointTest {

    @InjectMocks
    private ExpenseEndpoint expenseEndpoint;

    @Mock
    private ExpenseService expenseService;

    @Test
    public void should_return_a_list() {
        List<Expense> expenses = expenseEndpoint.get();

        assertThat(expenses, is(instanceOf(List.class)));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_an_error_when_there_is_an_error_on_the_service() {
        doThrow(new RuntimeException("Error accessing Database")) //
                .when(expenseService) //
                .getAll();

        expenseEndpoint.get();
    }

}