package org.example.mapper;

import org.example.model.SaleModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SaleRowMapper implements RowMapper<SaleModel> {
    @Override
    public SaleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
         return new SaleModel(
                 rs.getLong("id"),
                 rs.getLong("coin_id"),
                 rs.getString("name"),
                 rs.getString("ticker"),
                 rs.getLong("price"),
                 rs.getLong("qty"),
                 rs.getString("type")
         );
    }
}
