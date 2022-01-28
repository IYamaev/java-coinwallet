package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.example.dto.CoinDto;
import org.example.mapper.CoinMapper;
import org.example.model.CoinModel;
import org.example.service.CoinRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinManager {

    private final String defaultImage = "noimage.png";
    private final CoinRepository coinRepository;

    public List<CoinDto> getAll() {
        final List<CoinModel> items = coinRepository.getAll(); // получаем монеты из БД
        final List<CoinDto> coinDtos = new ArrayList<>();// создаём список DTO
        for (CoinModel item : items) { // бегаем по списку и преобразуем модели в DTO
            coinDtos.add(CoinMapper.toCoinDto(item));
        }
        return coinDtos; // возвращем список DTO
    }

    public CoinDto getById(long id) {
        final CoinModel byId = coinRepository.getById(id);
        return CoinMapper.toCoinDto(byId);
    }

    public CoinDto getByTicker(String ticker) {
        final CoinModel byTicker = coinRepository.getByTicker(ticker);
        return CoinMapper.toCoinDto(byTicker);
    }

    public int add(CoinDto requestDTO) {
        final List<CoinModel> items = coinRepository.getAll();
        boolean isPresented = items.stream().map(CoinModel::getTicker).toList().contains(requestDTO.getTicker());
        return isPresented ? 0 : coinRepository.add(requestDTO);
    }

    public void removeByTicker(String ticker) {
        coinRepository.removeByTicker(ticker);
    }

    public void restoreByTicker(String ticker) {
        coinRepository.restoreByTicker(ticker);
    }

    private String getImage(String image) {
        return image == null ? defaultImage : image;
    }
}
