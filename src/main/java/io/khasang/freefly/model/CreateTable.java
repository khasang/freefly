package io.khasang.freefly.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class CreateTable {

    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String create() {
        try {
            jdbcTemplate.execute("DROP TABLE  IF EXISTS cats");

            jdbcTemplate.execute("CREATE TABLE public.cats\n" +
                    "(\n" +
                    " id INTEGER NOT NULL, \n" +
                    " name character VARYING(255), \n " +
                    " description CHARACTER VARYING(255), \n" +
                    "color_id INTEGER, \n" +
                    " CONSTRAINT cats_pkey PRIMARY KEY (id)\n" +
                    ")");


            return "table created";
        } catch (Exception e){
            return  "something going wrong..." + e;
        }
    }
}
