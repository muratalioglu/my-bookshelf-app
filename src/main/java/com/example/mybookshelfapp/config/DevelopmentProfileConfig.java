package com.example.mybookshelfapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevelopmentProfileConfig {

    private final DataSource dataSource;

    public DevelopmentProfileConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    void populateDatabase() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("dev/db-scripts/schema.sql"));
        databasePopulator.addScript(new ClassPathResource("dev/db-scripts/data.sql"));
        databasePopulator.execute(dataSource);
    }
}