package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseEndpointTest {

    @InjectMocks
    private ExpenseEndpoint expenseEndpoint;

    @Mock
    private ExpenseService expenseService;

    @Test
    public void should_call_the_service() {
        expenseEndpoint.get();

        Mockito.verify(expenseService, Mockito.times(1)).getAll();
    }

    @Test
    public void should_return_a_list() {

        List<Expense> expenses = expenseEndpoint.get();

        assertThat(expenses, is(instanceOf(List.class)));
    }

}