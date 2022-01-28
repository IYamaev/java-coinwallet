 package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.model.CoinModel;
import org.example.service.CoinRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

 @Component
@RequiredArgsConstructor
public class MarketManager {
    private final NamedParameterJdbcTemplate template;
    private final CoinRepository coinRepository;

    @Scheduled(fixedRate = 1000)
    public void update() {
        final List<CoinModel> allCoins = coinRepository.getAll();
        for (CoinModel coin : allCoins) {
            System.out.println(coin.getName() + " " + coin.getPrice());
            final long price = coin.getPrice();
            BigDecimal newPrice = BigDecimal.valueOf(price + (new Random().nextInt(10)  * (Math.random() - 0.5)));
            coinRepository.updatePriceById(coin.getId(), newPrice);
        }
    }
 }
