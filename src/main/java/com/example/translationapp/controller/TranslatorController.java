package com.example.translationapp.controller;

import com.example.translationapp.controller.route.CommonRoute;
import com.example.translationapp.controller.route.TranslatorRoute;
import com.example.translationapp.service.ITranslatorService;
import com.example.translationapp.shared.ResponseEntityBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonRoute.BASE_API + CommonRoute.VERSION)
public class TranslatorController {
    private final ITranslatorService translatorService;

    public TranslatorController(ITranslatorService translatorService) {
        this.translatorService = translatorService;
    }
    @GetMapping(TranslatorRoute.BASE_URL)
    public ResponseEntity<?> getAllTranslators (
            @RequestParam(name = "offset", defaultValue = "0") final Integer offset,
            @RequestParam(name = "limit", defaultValue = "5") final Integer limit,
            @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
        var pageable = PageRequest.of(offset, limit);
        var productList = translatorService.getAllTranslation(keyword, pageable);
        return ResponseEntityBuilder
                .getBuilder()
                .setDetails(productList)
                .build();
    }
}
