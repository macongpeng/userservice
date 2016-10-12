package com.hoyn.userservice;

import com.hoyn.userservice.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by dennyma on 10/10/2016.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {
        log.info("Creating tables");

        if (jdbcTemplate != null) {
            jdbcTemplate.execute("DROP TABLE USER IF EXISTS");
            jdbcTemplate.execute("CREATE TABLE USER(" +
                    "id SERIAL, name VARCHAR(255), age INTEGER, salary DOUBLE PRECISION(12))");
        }
        else
        {
            log.warn("Could not initialize jdbcTemplate");
        }
    }
}
