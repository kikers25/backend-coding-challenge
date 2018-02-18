package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.engage.expense.config.LocalDateSerializer.FORMATTER;

@Component
public class JdbcTemplateExpenseDao {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Expense> getAll() {
        return jdbcTemplate.query("select * from Expense", (resultSet, i) -> toExpense(resultSet));
    }

    private Expense toExpense(ResultSet resultSet) throws SQLException {
        Expense expense = new Expense()
                .setReason(resultSet.getString("REASON"))
                .setVat(resultSet.getBigDecimal("VAT"))
                .setAmount(resultSet.getBigDecimal("AMOUNT"))
                .setDate(LocalDate.parse(resultSet.getString("DATE"), FORMATTER));
//        expenses.setId(resultSet.getLong("ID"));
        return expense;
    }
}
