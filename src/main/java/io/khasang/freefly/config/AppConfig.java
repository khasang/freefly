package io.khasang.freefly.config;

import io.khasang.freefly.dao.*;
import io.khasang.freefly.dao.impl.*;
import io.khasang.freefly.entity.*;
import io.khasang.freefly.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = "classpath:util.properties")
@PropertySource(value = "classpath:auth.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // WRONG!!! Not for production!
        //        dataSource.setDriverClassName("org.postgresql.Driver");
        //        dataSource.setUrl("jdbc:postgresql://localhost:5432/freefly");
        //        dataSource.setUsername("root");
        //        dataSource.setPassword("root");
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.user"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());
        jdbcDao.setUsersByUsernameQuery(environment.getRequiredProperty("userByQuery"));
        jdbcDao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcDao;
    }

    @Bean
    public CreateTable createTable() {
        return new CreateTable(jdbcTemplate());
    }

    @Bean
    public CatDao catDao() {
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDaoImpl(Employee.class);
    }

    @Bean
    public NotificationTypesDao notificationTypesDao() {
        return new NotificationTypesDaoImpl(NotificationTypes.class);
    }

    @Bean
    public NotificationDao notificationDao() {
        return new NotificationDaoImpl(Notification.class);
    }

    @Bean
    public SecurityLogDao securityLogDao() {
        return new SecurityLogDaoImpl(SecurityLog.class);
    }

    @Bean
    public TripsDao tripsDao() {
        return new TripsDaoImpl(Trips.class);
    }

}
