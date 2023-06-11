package com.fodala.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@MapperScan(basePackages = "com.fodala.mapper")
@Configuration
public class ToDoConfig {
    private static final Logger logger = LoggerFactory.getLogger(ToDoConfig.class);
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        createNewDatabase();
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    void createNewDatabase() {
        String url = env.getProperty("spring.datasource.url");
        try (Connection conn = DriverManager.getConnection(Objects.requireNonNull(url))) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                logger.info("The driver name is {}", meta.getDriverName());
                logger.info("A new database has been created.");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
