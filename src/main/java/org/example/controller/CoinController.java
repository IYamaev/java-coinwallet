package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.manager.CoinManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {

    @Autowired
    public CoinController(CoinManager manager) {
        this.manager = manager;
    }

    private final CoinManager manager;

    @GetMapping("/getAll")
    public List<CoinDto> getAll() {
        return manager.getAll();
    }

    @GetMapping("/getById")
    public CoinDto getById(@RequestParam long id) {
        return manager.getById(id);
    }

    @GetMapping("/getByTicker/{ticker}")
    public CoinDto getByTicker(@PathVariable String ticker) {
        return manager.getByTicker(ticker);
    }

    @PostMapping("/add")
    public int add(@RequestBody CoinDto requestDTO) {
        return manager.add(requestDTO);
    }

    @PostMapping("/removeByTicker")
    public void removeByTickerFromParam(@RequestParam String ticker) {
        manager.removeByTicker(ticker);
    }

    @PostMapping("/restoreByTicker")
    public void restoreByTickerFromParam(@RequestParam String ticker) {manager.restoreByTicker(ticker);
    }
}
