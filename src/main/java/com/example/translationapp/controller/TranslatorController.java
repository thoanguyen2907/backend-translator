package com.example.translationapp.controller;

import com.example.translationapp.controller.route.CommonRoute;
import com.example.translationapp.controller.route.TranslatorRoute;
import com.example.translationapp.dto.TranslatorUpdateDto;
import com.example.translationapp.service.ITranslatorService;
import com.example.translationapp.shared.ResponseEntityBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(TranslatorRoute.BASE_URL + "/{translatorId}")
    public ResponseEntity<?> updateTranslator (@PathVariable final String translatorId, @RequestBody TranslatorUpdateDto translatorUpdateDto) {
        var convertedId = Integer.parseInt(translatorId);
        var translatorResponse =  translatorService.updateTranslator(convertedId, translatorUpdateDto);
        return ResponseEntity.ok(translatorResponse);
    }
}
