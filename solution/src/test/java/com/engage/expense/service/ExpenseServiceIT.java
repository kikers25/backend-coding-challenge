package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseServiceIT {

    @Autowired
    private JdbcTemplateExpenseDao dao;

    @Test
    public void should_have_expenses() {
        List<Expense> all = dao.getAll();

        assertThat(all.isEmpty(), is(false));
    }
}