package io.khasang.freefly.model;

import org.springframework.jdbc.core.JdbcTemplate;


public class UpdateRow {

    private JdbcTemplate jdbcTemplate;

    public UpdateRow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UpdateRow() {
    }

    public String update() {
        try {
            String sql;
            sql = "UPDATE cats " +
                    "SET " +
                    "name = case " +
                    "when name = 'Барсик' then 'Рыжик' " +
                    "when name = 'Рыжик'  then 'Барсик' " +
                    "end " +
                    "WHERE id = 1";
            jdbcTemplate.execute(sql);
            return "update good";
        } catch (Exception e) {
            return "update fail " + e;
        }
    }
}
