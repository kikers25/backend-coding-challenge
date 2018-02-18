package com.engage.expense.service;

import com.engage.expense.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
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

    public long save(Expense expense) {
        String sql = "INSERT INTO EXPENSE (DATE, AMOUNT, VAT, REASON) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
                    ps.setString(1, expense.getDate().format(FORMATTER));
                    ps.setString(2, expense.getAmount().toString());
                    ps.setString(3, "0.00"); // TODO: VAT
                    ps.setString(4, expense.getReason());
                    return ps;
                }, keyHolder);
        Number key = keyHolder.getKey();
        return key.longValue();
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
