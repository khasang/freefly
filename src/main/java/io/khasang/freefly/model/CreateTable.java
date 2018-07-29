package io.khasang.freefly.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTable {

    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {
    }

    public String create(){

        try{
        String sql;
        sql = "DROP TABLE IF EXISTS cats";
        jdbcTemplate.execute(sql);
        sql = "CREATE TABLE cats " +
                "(" +
                "id INTEGER NOT NULL, " +
                "name CHARACTER VARYING(255) NOT NULL, " +
                "description CHARACTER VARYING(255), " +
                "id_color INTEGER, " +
                "CONSTRAINT cats_pk PRIMARY KEY(id) " +
                ")";
        jdbcTemplate.execute(sql);
        return "table cats created";
        } catch (Exception e) {
            return "created table cats fail: " + e;
        }

    }
}
