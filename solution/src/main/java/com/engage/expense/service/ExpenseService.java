package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ExpenseService {

    private Logger logger = LoggerFactory.getLogger(ExpenseService.class.getName());

    private JdbcTemplateExpenseDao jdbcTemplateExpenseDao;

    public static final BigDecimal PERCENTAGE = new BigDecimal(20);
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Autowired
    public ExpenseService(JdbcTemplateExpenseDao jdbcTemplateExpenseDao) {
        this.jdbcTemplateExpenseDao = jdbcTemplateExpenseDao;
    }

    public List<Expense> getAll() {

        return jdbcTemplateExpenseDao.getAll();
    }

    public Expense add(Expense expense) {

        calculateVAT(expense);
        logger.info("expense: " + expense);

        jdbcTemplateExpenseDao.save(expense);

        return expense;
    }

    private void calculateVAT(Expense expense) {
        BigDecimal amount = expense.getAmount();
        BigDecimal vat = amount.multiply(PERCENTAGE).divide(ONE_HUNDRED);

        if (!isIntegerValue(amount)) {
            vat = vat.setScale(2, RoundingMode.CEILING);
        }

        expense.setVat(vat);
    }

    private boolean isIntegerValue(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }
}
