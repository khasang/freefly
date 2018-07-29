package io.khasang.freefly.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class InsertRow {

    private JdbcTemplate jdbcTemplate;

    public InsertRow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public InsertRow() {
    }

    public String insert(){
        try{
            String sql;
            sql = "INSERT INTO cats " +
                    "(id, name) VALUES (1, 'Барсик')";
            jdbcTemplate.execute(sql);
            return "row insert";
        } catch (Exception e) {
            return "insert fail: " + e;
        }
    }
}
