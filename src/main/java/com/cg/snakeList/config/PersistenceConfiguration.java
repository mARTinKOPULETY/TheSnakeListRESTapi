package com.cg.snakeList.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.jdbc.*;
import org.springframework.context.annotation.*;

import javax.sql.*;

@Configuration
public class PersistenceConfiguration {



    @Bean
    public DataSource  dataSource(@Value("${DB_URL}") String DB_URL, @Value("${DB_PASS}") String DB_PASS){
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName("com.mysql.cj.jdbc.Driver");
        builder.url(DB_URL);
        builder.username("root");
        builder.password(DB_PASS);

        System.out.println("My custom datasource hean has benn initialized and set.");
        return  builder.build();
    }
}