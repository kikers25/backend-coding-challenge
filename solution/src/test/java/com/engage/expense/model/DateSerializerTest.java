package com.engage.expense.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class DateSerializerTest {

    @Test
    public void should_serializer_with_right_format() throws Exception {
        String toParse = "01/01/2018";
        Expense expense = new Expense().setDate(LocalDate.of(2018, 1, 1));

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(expense);

        assertThat(result, containsString(toParse));
    }

}