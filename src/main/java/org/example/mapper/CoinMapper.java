package org.example.mapper;

import org.example.dto.CoinDto;
import org.example.model.CoinModel;

public class CoinMapper {
    public static CoinDto toCoinDto(CoinModel model){
        final CoinDto coinDto = new CoinDto();
        coinDto.setId(model.getId());
        coinDto.setImage(model.getImage());
        coinDto.setQty(model.getQty());
        coinDto.setTicker(model.getTicker());
        coinDto.setName(model.getName());
        coinDto.setPrice(model.getPrice());
        return coinDto;
    }
}
