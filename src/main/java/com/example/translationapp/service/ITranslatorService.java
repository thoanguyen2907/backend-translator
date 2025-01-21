package com.example.translationapp.service;

import com.example.translationapp.dto.TranslatorResponseDto;
import com.example.translationapp.dto.TranslatorUpdateDto;
import com.example.translationapp.model.entity.TranslationEntity;
import com.example.translationapp.shared.PageList;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITranslatorService {
    PageList<TranslatorResponseDto> getAllTranslation(String keyword, Pageable pageable);
    List<TranslationEntity> save(List<TranslationEntity> translationEntities);
    TranslatorResponseDto updateTranslator(Integer translatorId, TranslatorUpdateDto translatorUpdateDto);
}
