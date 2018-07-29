package io.khasang.freefly.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class Select {
    private JdbcTemplate jdbcTemplate;

    public Select(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Select() {
    }

    public String select(){
        try{
            String sql;
            sql = "select * from cats";
            jdbcTemplate.execute(sql);
            return "select done";
        } catch (Exception e) {
            return "select fail: " + e;
        }
    }
}
