package com.engage.expense.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
@Component
public class Init {


    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello World";
    }
}
