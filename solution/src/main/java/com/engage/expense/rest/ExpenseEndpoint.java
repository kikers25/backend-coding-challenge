package com.engage.expense.rest;

import com.engage.expense.model.Expense;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collections;
import java.util.List;

@Path("/expenses")
@Component
public class ExpenseEndpoint {


    @GET
    @Produces("application/json")
    public List<Expense> root() {

        Expense expense = new Expense() //
                .setAmount("10.15") //
                .setDate("01/01/2015") // dd
                .setReason("dinner with lead") //
                .setVat("2.03");
        return Collections.singletonList(expense);

        // <div ng-repeat="expense in expenses" class="body-row">
        //			<div class="col-4">{{expense.date | date:'dd MMM yyyy'}}</div>
        //			<div class="col-4 figures">{{expense.amount | currency:"&pound;"}}</div>
        //			<div class="col-2 figures">{{expense.vat | currency:"&pound;"}}</div>
        //			<div class="col-15">{{expense.reason}}</div>
        //		</div>
    }


}
