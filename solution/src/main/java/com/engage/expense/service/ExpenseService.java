package com.engage.expense.service;

import com.engage.expense.model.Expense;
import com.engage.expense.rest.ExpenseEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ExpenseService {

    private Logger logger = LoggerFactory.getLogger(ExpenseService.class.getName());

    @Autowired
    JdbcTemplateExpenseDao jdbcTemplateExpenseDao;

    public List<Expense> getAll() {

        return jdbcTemplateExpenseDao.getAll();
    }

    public void add(Expense expense) {
        logger.info("expense: " + expense);
    }
}
