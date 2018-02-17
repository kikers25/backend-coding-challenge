package com.engage.expense.config;

import com.engage.expense.rest.ExpenseEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/app")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ExpenseEndpoint.class);
    }

}
