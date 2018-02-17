package com.engage.expense.config;

import com.engage.expense.controller.Init;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Init.class);
    }

}
