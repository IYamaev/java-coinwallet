package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CoinDto;
import org.example.exception.CoinNotFoundException;
import org.example.mapper.CoinRowMapper;
import org.example.model.CoinModel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CoinRepository {
    private final NamedParameterJdbcTemplate template;
    private final CoinRowMapper coinRowMapper;

    public List<CoinModel> getAll() {
        return template.query(
                // language=PostgreSQL
                """
                        SELECT  id, name, ticker, price, qty, image FROM coins
                        WHERE removed = FALSE
                        ORDER BY id
                        LIMIT 10
                        """,
                coinRowMapper
        );
    }

    public CoinModel getById(long id) {
        try {
            return template.queryForObject(
                    // language=PostgreSQL
                    """
                            SELECT  id, name, ticker, price, qty, image FROM coins
                            WHERE id = :id AND removed = FALSE
                            """,
                    Map.of("id", id),
                    coinRowMapper
            );
        } catch (EmptyResultDataAccessException e) {
            throw new CoinNotFoundException(e);
        }
    }

    public CoinModel getByTicker(String ticker) {
        try {
            return template.queryForObject(
                    // language=PostgreSQL
                    """
                            SELECT  id, name, ticker, price, qty, image FROM coins
                            WHERE ticker = :ticker AND removed = FALSE
                            """,
                    Map.of("ticker", ticker),
                    coinRowMapper
            );
        } catch (EmptyResultDataAccessException e) {
            throw new CoinNotFoundException(e);
        }
    }

    public void updatePriceById(long id, BigDecimal newPrice) {
        final Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        map.put("newPrice", newPrice.longValue());

        try {
            template.query("""
                    UPDATE coins
                    SET price = :newPrice
                    WHERE id = :id
                    """, map, a -> null);
        } catch (Exception e) {
            //System.out.println();
        }
    }

    public int add(CoinDto requestDTO) {
         return template.update("insert into COINS(name, ticker, price, qty, image) " +
                        "values (:name, :ticker, :price, :qty, :image)",
                                Map.of(
                        "name", requestDTO.getName(),
                        "ticker", requestDTO.getTicker(),
                        "price", requestDTO.getPrice(),
                        "qty", requestDTO.getQty(),
                        "image", requestDTO.getImage())
                );
    }

    public void removeByTicker(String ticker) {
        final int affected = template.update(
                //language=PostgreSQL
                """
                        UPDATE coins SET removed = TRUE WHERE ticker = :ticker
                        """,
                Map.of("ticker", ticker)
        );
        if (affected == 0) {
            throw new CoinNotFoundException("coin with ticker " + ticker + " not found");
        }
    }

    public void restoreByTicker(String ticker) {
        final int affected = template.update(
                //language=PostgreSQL
                """
                        UPDATE coins SET removed = FALSE WHERE ticker = :ticker
                        """,
                Map.of("ticker", ticker)
        );
        if (affected == 0) {
            throw new CoinNotFoundException("coin with ticker " + ticker + " restored");
        }
    }
}
