package org.example.mapper;

import org.example.model.SaleCoinModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SaleCoinRowMapper implements RowMapper<SaleCoinModel> {
    @Override
    public SaleCoinModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SaleCoinModel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("ticker"),
                rs.getLong("price"),
                rs.getLong("qty")
        );
    }
}
