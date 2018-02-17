package com.engage.expense.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/expenses")
@Component
public class Expenses {


    @GET
    @Produces("application/json")
    public String root() {
        return "[]";
    }
}
