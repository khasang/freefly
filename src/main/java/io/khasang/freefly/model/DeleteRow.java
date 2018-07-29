package io.khasang.freefly.model;

import org.springframework.jdbc.core.JdbcTemplate;


public class DeleteRow {

    private JdbcTemplate jdbcTemplate;

    public DeleteRow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DeleteRow() {
    }

    public String delete(){
        try{
            String sql;
            sql = "DELETE FROM cats " +
                    "WHERE id = 1";
            jdbcTemplate.execute(sql);
            return "row delete";
        } catch (Exception e) {
            return "delete fail: " + e;
        }
    }
}
