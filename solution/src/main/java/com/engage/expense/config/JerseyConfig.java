package com.engage.expense.config;

import com.engage.expense.rest.ExpenseEndpoint;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/app")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ExpenseEndpoint.class);
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setTitle("Expenses app");
        config.setVersion("v1");
        config.setSchemes(new String[] { "http" });
        config.setBasePath("/app");
        config.setResourcePackage("com.engage.expense.rest");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
