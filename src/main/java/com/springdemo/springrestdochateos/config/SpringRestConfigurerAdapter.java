package com.springdemo.springrestdochateos.config;

import com.springdemo.springrestdochateos.entities.Employee;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy
            .RepositoryDetectionStrategies.ANNOTATED)
            .exposeIdsFor(Employee.class);
    }

}
