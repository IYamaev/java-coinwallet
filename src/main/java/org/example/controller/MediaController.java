package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UploadSingleMediaResponseDTO;
import org.example.manager.MediaManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cryptowallet")
@RequiredArgsConstructor
public class MediaController {
    private final MediaManager manager;

    @PostMapping("/bytes")
    public UploadSingleMediaResponseDTO upload(@RequestBody byte[] bytes, @RequestHeader("Content-Type") String contentType) {
        return manager.save(bytes, contentType);
    }

    // @RequestBody - когда приходит запрос, собирает весь наш файл 'CryptoWallet.jpg' и кладёт в массив данных 'byte[] bytes'.
    // @RequestHeader - берёт название в 'requests' из 'Content-Type' и задаёт ему переменную 'String contentType'.

    @PostMapping("/multipart")
    public UploadSingleMediaResponseDTO upload(@RequestPart MultipartFile file) {
        return manager.save(file);
    }
}
