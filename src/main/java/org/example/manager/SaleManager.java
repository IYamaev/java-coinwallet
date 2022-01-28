package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.SaleRegisterRequestDTO;
import org.example.dto.SaleRegisterResponseDTO;
import org.example.exception.CoinNotFoundException;
import org.example.exception.CoinPriceChangeException;
import org.example.exception.CoinQtyNotEnoughException;
import org.example.exception.SaleRegistrationException;
import org.example.mapper.CoinRowMapper;
import org.example.mapper.SaleCoinRowMapper;
import org.example.mapper.SaleRowMapper;
import org.example.model.CoinModel;
import org.example.model.SaleCoinModel;
import org.example.model.SaleModel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SaleManager {
    private final NamedParameterJdbcTemplate template;
    private final SaleCoinRowMapper saleCoinRowMapper;
    private final SaleRowMapper saleRowMapper;

    @Transactional
    public SaleRegisterResponseDTO register(SaleRegisterRequestDTO requestDTO) {
        try {
            final SaleCoinModel item = template.queryForObject(
                    // language=PostgreSQL
                    """
                            SELECT id, name, ticker, price, qty FROM coins
                            WHERE id = :id AND removed = FALSE
                            """,
                    Map.of("id", requestDTO.getCoinId()),
                    saleCoinRowMapper
            );
            if (item.getQty() < requestDTO.getQty()) {
                throw new CoinQtyNotEnoughException("coin with id " + item.getId() + " has qty " + item.getQty() + " but requested " + requestDTO.getQty());
            }

            if (item.getPrice() < requestDTO.getPrice()) {
                final SaleRegisterResponseDTO.Sale sale = new SaleRegisterResponseDTO.Sale();
                final SaleRegisterResponseDTO saleRegisterResponseDTO = new SaleRegisterResponseDTO();
                sale.setName("Продажа монеты не прошла, так как актуальная цена ниже лимитного ордера на продажу = " + item.getPrice());
                saleRegisterResponseDTO.setSale(sale);
                return saleRegisterResponseDTO;
            }

            if (requestDTO.getType().equals("sale")) {
                final int affected = template.update(
                        // language=PostgreSQL
                        """
                                UPDATE coins SET qty = qty - :saleQty WHERE id = :coinId AND removed = FALSE
                                """,
                        Map.of(
                                "coinId", requestDTO.getCoinId(),
                                "saleQty", requestDTO.getQty()
                        )
                );

                if (affected == 0) {
                    throw new SaleRegistrationException("can't update qty with value " + requestDTO.getQty() + " for coin with id " + requestDTO.getCoinId());
                }
            }

            if (requestDTO.getType().equals("buy")) {
                final int affected = template.update(
                        // language=PostgreSQL
                        """
                                UPDATE coins SET qty = qty + :saleQty WHERE id = :coinId AND removed = FALSE
                                """,
                        Map.of(
                                "coinId", requestDTO.getCoinId(),
                                "saleQty", requestDTO.getQty()
                        )
                );
                if (affected == 0) {
                    throw new SaleRegistrationException("can't update qty with value " + requestDTO.getQty() + " for coin with id " + requestDTO.getCoinId());
                }
            }

            final SaleModel sale = template.queryForObject(
                    // language=PostgreSQL
                    """
                            INSERT INTO sales (coin_id, name, ticker, price, qty, type) VALUES (:coinId, :name, :ticker, :price, :qty, :type)
                            RETURNING id, coin_id, name, ticker, price, qty, type
                            """,
                    Map.of(
                            "coinId", requestDTO.getCoinId(),
                            "name", item.getName(),
                            "ticker", item.getTicker(),
                            "price", item.getPrice(),
                            "qty", requestDTO.getQty(),
                            "type", requestDTO.getType()
                    ),
                    saleRowMapper
            );

            return new SaleRegisterResponseDTO(new SaleRegisterResponseDTO.Sale(
                    sale.getId(),
                    sale.getCoinId(),
                    sale.getName(),
                    sale.getTicker(),
                    sale.getPrice(),
                    sale.getQty()
            ));


        } catch (EmptyResultDataAccessException e) {
            throw new CoinNotFoundException(e);
        }
    }
}
