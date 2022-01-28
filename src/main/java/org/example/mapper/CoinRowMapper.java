package org.example.mapper;

import org.example.model.CoinModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CoinRowMapper implements RowMapper<CoinModel> {
    @Override
    public CoinModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CoinModel (
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("ticker"),
                rs.getLong("price"),
                rs.getLong("qty"),
                rs.getString("image")
        );
    }
}
